package com.taztag.fingerprintservice.client;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import com.taztag.fingerprintservice.IFingerprintService;
import com.taztag.fingerprintservice.IFingerprintService.Stub;
import com.upek.android.ptapi.PtConnectionAdvancedI;

public abstract class FingerprintActivity extends Activity {
    private static final int CLOSE_CONNECTION = 6;
    private static final int CLOSE_FAILED = 3;
    private static final String CLOSE_FP_INTENT = "com.taztag.fingerprintservice.CLOSE_FINGERPRINT_EVENT";
    private static final int CLOSE_OK = 2;
    private static final int CONNECTION_READY = 4;
    private static final int CONNECTION_UNAVAILABLE = 5;
    private static final int FP_DISCONNECTED = 7;
    private static final String FP_SERVICE = "FingerprintService";
    private static final String FP_SERVICE_PKG = "com.taztag.fingerprintservice";
    private static final int OPEN_FAILED = 1;
    private static final String OPEN_FP_INTENT = "com.taztag.fingerprintservice.OPEN_FINGERPRINT_EVENT";
    private static final int OPEN_OK = 0;
    private static final String TAG = FingerprintActivity.class.getName();
    private Handler broadcastHandler = null;
    private final Object cleanUp = new Object();
    private Thread connection;
    private AtomicBoolean fingerprintAvailable = new AtomicBoolean(true);
    private FingerprintConnection fp_connection = null;
    private IFingerprintService fp_service = null;
    private FingerprintServiceConnection fp_service_connection;
    private volatile boolean hasConnection = false;
    private int id_connection = -1;
    private volatile boolean mIsBound = false;
    private BroadcastReceiver mReceiver = null;
    private volatile boolean mReceiver_registered = false;
    private String packageName = null;
    private PtConnectionAdvancedI ptapi_connection = null;
    private volatile boolean registered = false;

    class C02101 extends Thread {
        C02101() {
        }

        public void run() {
            FingerprintActivity.this.connectToService();
        }

        public void interrupt() {
            Log.d(FingerprintActivity.TAG, "Interrupt Thread Connection");
        }
    }

    class FingerprintServiceConnection implements ServiceConnection {
        FingerprintServiceConnection() {
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            FingerprintActivity.this.mIsBound = true;
            Log.d(FingerprintActivity.TAG, "remote service connected");
            FingerprintActivity.this.fp_connection = new FingerprintConnection();
            FingerprintActivity.this.fp_service = Stub.asInterface(service);
            Log.d(FingerprintActivity.TAG, "register to the service...");
            if (!FingerprintActivity.this.registered) {
                FingerprintActivity.this.register();
            }
            if (!FingerprintActivity.this.hasConnection) {
                Log.d(FingerprintActivity.TAG, "ask for ptapi connection...");
                Log.d(FingerprintActivity.TAG, "Get connection...");
                FingerprintActivity.this.get_connection();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            Log.d(FingerprintActivity.TAG, "remote service disconnected");
        }
    }

    public class ServiceReceiver extends BroadcastReceiver {
        private boolean closed = false;

        public void onReceive(Context ctx, Intent intent) {
            Log.d(FingerprintActivity.TAG, "onReceive : " + intent.getAction());
            if (intent.getAction().equals(FingerprintActivity.OPEN_FP_INTENT)) {
                Log.d(FingerprintActivity.TAG, "OPEN_FP_INTENT received");
                FingerprintActivity.this.fingerprintAvailable.set(true);
                FingerprintActivity.this.launchServiceConnection();
                this.closed = false;
            }
            if (intent.getAction().equals(FingerprintActivity.CLOSE_FP_INTENT) && !this.closed) {
                Log.d(FingerprintActivity.TAG, "CLOSE_FP_INTENT received");
                FingerprintActivity.this.fingerprintAvailable.set(false);
                FingerprintActivity.this.hasConnection = false;
                this.closed = true;
                synchronized (FingerprintActivity.this.cleanUp) {
                    FingerprintActivity.this.onFingerprintConnectionLost();
                    Log.d(FingerprintActivity.TAG, "Disconnect from service");
                    FingerprintActivity.this.close_connection();
                    FingerprintActivity.this.disconnect_from_service();
                    if (FingerprintActivity.this.connection != null) {
                        FingerprintActivity.this.connection.interrupt();
                        FingerprintActivity.this.connection = null;
                    }
                }
            }
        }
    }

    public abstract void onFingerprintConnectionLost();

    public abstract void onFingerprintError(String str);

    public abstract void onFingerprintReady(PtConnectionAdvancedI ptConnectionAdvancedI);

    public abstract void onFingerprintUnavailable();

    private void get_connection() {
        boolean connection_ready = false;
        int attempt = 0;
        while (this.fingerprintAvailable.get() && !connection_ready && attempt < 40) {
            Log.d(TAG, "Trying to get PTAPI connection, attempt #" + attempt);
            try {
                int status = this.fp_service.get_PTAPI_Connection(this.id_connection);
                if (status == 4) {
                    connection_ready = true;
                    this.hasConnection = true;
                    this.ptapi_connection = this.fp_connection.open();
                    if (this.ptapi_connection != null) {
                        onFingerprintReady(this.ptapi_connection);
                    } else {
                        this.fp_service.setState(this.id_connection, 1);
                        onFingerprintError("Open failed");
                    }
                } else if (status == 7) {
                    this.fingerprintAvailable.set(false);
                    onFingerprintConnectionLost();
                    Log.d(TAG, "FP Disconnected");
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            attempt++;
        }
        if (!connection_ready) {
            onFingerprintUnavailable();
        }
    }

    private void close_connection() {
        if (this.fp_connection != null && this.hasConnection) {
            this.fp_connection.close();
            this.fp_connection = null;
            this.hasConnection = false;
            if (this.fp_service != null) {
                try {
                    this.fp_service.setState(this.id_connection, 2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void register() {
        if (this.fp_service != null) {
            try {
                this.id_connection = this.fp_service.register(1, this.packageName);
                this.registered = true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void unRegister() {
        if (!(this.fp_service == null || this.id_connection == -1)) {
            try {
                this.fp_service.unregister(this.id_connection);
                this.registered = false;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        this.id_connection = -1;
    }

    private void launchServiceConnection() {
        if (!this.mIsBound) {
            this.connection = new C02101();
            this.connection.start();
        }
    }

    private void connectToService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(FP_SERVICE_PKG, "com.taztag.fingerprintservice.FingerprintService"));
        this.fp_service_connection = new FingerprintServiceConnection();
        if (!bindService(intent, this.fp_service_connection, 1)) {
            onFingerprintUnavailable();
        }
    }

    private void disconnect_from_service() {
        if (this.mIsBound) {
            Log.d(TAG, "unRegister/unBind : disconnect_from_service");
            unRegister();
            if (this.fp_service_connection != null) {
                unbindService(this.fp_service_connection);
            }
            this.mIsBound = false;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (getApplicationInfo() != null) {
            this.packageName = getApplicationInfo().packageName;
        }
    }

    public void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    public void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        IntentFilter intentFilterCloseFP = new IntentFilter(CLOSE_FP_INTENT);
        IntentFilter intentFilterOpenFP = new IntentFilter(OPEN_FP_INTENT);
        this.mReceiver = new ServiceReceiver();
        Log.d(TAG, "RegisterReceiver");
        registerReceiver(this.mReceiver, intentFilterCloseFP, null, this.broadcastHandler);
        registerReceiver(this.mReceiver, intentFilterOpenFP, null, this.broadcastHandler);
        this.mReceiver_registered = true;
        Log.d(TAG, "Connect to service");
        launchServiceConnection();
    }

    public void onPause() {
        synchronized (this.cleanUp) {
            Log.d(TAG, "onPause");
            onFingerprintConnectionLost();
            Log.d(TAG, "Disconnect from service");
            close_connection();
            disconnect_from_service();
            if (this.mReceiver_registered) {
                unregisterReceiver(this.mReceiver);
                this.mReceiver_registered = false;
            } else {
                Log.d(TAG, "onPause : mReceiver==null");
            }
            if (this.connection != null) {
                this.connection.interrupt();
                this.connection = null;
            }
        }
        super.onPause();
    }

    public void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        synchronized (this.cleanUp) {
            close_connection();
            disconnect_from_service();
            if (this.mReceiver_registered) {
                unregisterReceiver(this.mReceiver);
                this.mReceiver_registered = false;
            } else {
                Log.d(TAG, "onDestroy : mReceiver==null");
            }
            if (this.connection != null) {
                this.connection.interrupt();
                this.connection = null;
            }
        }
        super.onDestroy();
    }
}
