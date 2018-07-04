package com.taztag.fingerprintservice.client;

import com.upek.android.ptapi.PtConnectionAdvancedI;
import com.upek.android.ptapi.PtConstants;
import com.upek.android.ptapi.PtException;
import com.upek.android.ptapi.PtGlobal;
import com.upek.android.ptapi.interfaces.IPtapiConnection;
import com.upek.android.ptapi.struct.PtDeviceListItem;
import com.upek.android.ptapi.struct.PtInfo;
import com.upek.android.ptapi.struct.PtSessionCfgV5;

import android.util.Log;

class FingerprintConnection implements IPtapiConnection {
    private static final String TAG = FingerprintConnection.class.getName();
    private static final String[] mDSNUsb = new String[]{"usb,timeout=500", "wbf,timeout=500"};
    private static final int miRunningOnRealHardware = 1;
    private static final String[] msDSNSerial = new String[]{"port=/dev/ttyHS1,initspeed=2500000,speed=2500000"};
    private static String mydsn = "";
    private PtConnectionAdvancedI mConn = null;
    private PtGlobal mPtGlobal = null;
    private PtInfo mSensorInfo = null;
    private String msNvmPath = null;

    FingerprintConnection() {
    }

    public PtConnectionAdvancedI open() {
        if (initializePtapi()) {
            openPtapiSession();
        }
        return this.mConn;
    }

    public boolean close() {
        Log.d(TAG, "closeSession...");
        boolean statusClose = closeSession();
        Log.d(TAG, "terminatePtapi...");
        return statusClose && terminatePtapi();
    }

    public int errorCode() {
        return 0;
    }

    private boolean initializePtapi() {
        this.mPtGlobal = new PtGlobal();
        try {
            Log.d(TAG, "initialize");
            this.mPtGlobal.initialize();
            return true;
        } catch (UnsatisfiedLinkError ule) {
            Log.e(TAG, "initializePtapi : UnsatisfiedLinkError", ule);
            this.mPtGlobal = null;
            return false;
        } catch (PtException e) {
            if (e.getCode() != PtException.PT_STATUS_API_ALREADY_INITIALIZED) {
                Log.e(TAG, "initializePtapi : PtException", e);
                return false;
            }
            Log.d(TAG, "initializePtapi : Already initialized");
            return true;
        }
    }

    private void openPtapiSession() {
        PtException openException = null;
        if (mydsn.equals("")) {
            int i = 0;
            while (i < msDSNSerial.length) {
                try {
                    Log.d(TAG, "serial : " + i + " : " + msDSNSerial[i]);
                    openPtapiSessionInternal(msDSNSerial[i]);
                    return;
                } catch (PtException e) {
                    openException = e;
                    i++;
                }
            }
            for (i = 0; i < mDSNUsb.length; i++) {
                try {
                    Log.d(TAG, "usb : " + i + " : " + mDSNUsb[i]);
                    PtDeviceListItem[] devices = this.mPtGlobal.enumerateDevices(mDSNUsb[i]);
                    mydsn = mDSNUsb[i];
                    int d = 0;
                    while (d < devices.length) {
                        String dsn = devices[d].dsnSubString;
                        try {
                            Log.d(TAG, "usb : " + i + " : " + dsn);
                            mydsn = dsn;
                            openPtapiSessionInternal(dsn);
                            return;
                        } catch (PtException e2) {
                            openException = e2;
                            d++;
                        }
                    }
                    continue;
                } catch (PtException e1) {
                    if (e1.getCode() != PtException.PT_STATUS_INVALID_PARAMETER) {
                        return;
                    }
                }
            }
        } else {
            try {
                Log.d(TAG, "MyDsn : " + mydsn);
                openPtapiSessionInternal(mydsn);
                return;
            } catch (PtException e22) {
                openException = e22;
                mydsn = "";
            }
        }
        if (openException != null) {
        }
    }

    private void openPtapiSessionInternal(String dsn) throws PtException {
        Log.d(TAG, "openPtapiSessionInternal");
        this.mConn = (PtConnectionAdvancedI) this.mPtGlobal.open(dsn);
        try {
            this.mSensorInfo = this.mConn.info();
            configureOpenedDevice();
        } catch (PtException e) {
            if (e.getCode() == PtException.PT_STATUS_EMULATED_NVM_INVALID_FORMAT || e.getCode() == PtException.PT_STATUS_NVM_INVALID_FORMAT || e.getCode() == PtException.PT_STATUS_NVM_ERROR) {
                dsn = dsn + ",nvmprefix=" + this.msNvmPath + '/';
                this.mConn.close();
                this.mConn = null;
                this.mConn = (PtConnectionAdvancedI) this.mPtGlobal.open(dsn);
                try {
                    this.mSensorInfo = this.mConn.info();
                    configureOpenedDevice();
                } catch (PtException e2) {
                    this.mConn.formatInternalNVM(0, null, null);
                    this.mConn.close();
                    this.mConn = null;
                    this.mConn = (PtConnectionAdvancedI) this.mPtGlobal.open(dsn);
                    this.mSensorInfo = this.mConn.info();
                    if ((this.mSensorInfo.sensorType & PtConstants.PT_SENSORBIT_CALIBRATED) == 0) {
                        this.mConn.calibrate(2);
                        this.mSensorInfo = this.mConn.info();
                    }
                }
            } else {
                throw e;
            }
        }
    }

    private void configureOpenedDevice() throws PtException {
        PtSessionCfgV5 sessionCfg = (PtSessionCfgV5) this.mConn.getSessionCfgEx((short) 5);
        sessionCfg.sensorSecurityMode = (byte) 0;
        sessionCfg.callbackLevel |= 262144;
        switch (0) {
            case 0:
                sessionCfg.multipleEnroll = (byte) 0;
                break;
            case 1:
                sessionCfg.multipleEnroll = (byte) 1;
                sessionCfg.enrollMinTemplates = (byte) 3;
                sessionCfg.enrollMaxTemplates = (byte) 5;
                break;
            case 2:
                sessionCfg.multipleEnroll = (byte) 1;
                sessionCfg.enrollMinTemplates = (byte) 5;
                sessionCfg.enrollMaxTemplates = (byte) 10;
                break;
        }
        this.mConn.setSessionCfgEx((short) 5, sessionCfg);
    }

    private boolean terminatePtapi() {
        boolean status = true;
        Log.d(TAG, "terminatePtapi");
        try {
            if (this.mPtGlobal != null) {
                this.mPtGlobal.terminate();
            }
        } catch (PtException e) {
            Log.d(TAG, "error terminatePtapi");
            status = false;
        }
        this.mPtGlobal = null;
        return status;
    }

    private boolean closeSession() {
        boolean status = true;
        Log.d(TAG, "+closeSession");
        if (this.mConn != null) {
            try {
                this.mConn.closeDevice();
            } catch (PtException e) {
                Log.d(TAG, "error closeSession");
                status = false;
            }
            this.mConn = null;
        }
        Log.d(TAG, "-closeSession");
        return status;
    }
}
