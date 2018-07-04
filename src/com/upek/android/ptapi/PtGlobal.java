package com.upek.android.ptapi;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.upek.android.ptapi.callback.PtCommCallback;
import com.upek.android.ptapi.struct.PtDeviceListItem;
import com.upek.android.ptapi.struct.PtGlobalInfo;

public class PtGlobal implements PtGlobalI {
    public static final int apiVersion = 66304;
    public static int dllVersion = 0;
    private static boolean isLibraryLoaded = false;
    private static final long serialVersionUID = 8136932780925260899L;
    protected static List<PtConnection> watchQueue = new LinkedList();

    public native PtDeviceListItem[] enumerateDevices(String str) throws PtException;

    public native PtGlobalInfo globalInfo() throws PtException;

    public native void initialize() throws PtException;

    public native void initializeEx(int i) throws PtException;

    public native PtConnectionI open(String str) throws PtException;

    public native PtConnectionI openDevice(String str) throws PtException;

    public native PtConnectionI openRemote(PtCommCallback ptCommCallback) throws PtException;

    public native void terminate() throws PtException;

    protected void watchConnection(PtConnection connection) {
        synchronized (watchQueue) {
            if (connection != null) {
                watchQueue.add(connection);
            }
        }
    }

    public static void removeWatchConnection(int handle) {
        synchronized (watchQueue) {
            Iterator<PtConnection> it = watchQueue.iterator();
            while (it.hasNext()) {
                if (((PtConnection) it.next()).hConnection == handle) {
                    it.remove();
                }
            }
        }
    }

    protected void forceCloseConnections() {
        synchronized (watchQueue) {
            for (PtConnection item : watchQueue) {
                synchronized (item) {
                    try {
                        if (item.hConnection != 0) {
                            item.close();
                        }
                    } catch (PtException e) {
                    }
                }
            }
        }
    }

    public PtGlobal() {
        synchronized (PtGlobal.class) {
            if (!isLibraryLoaded) {
                loadLibrary();
            }
        }
    }

    public static synchronized void loadLibrary() {
        synchronized (PtGlobal.class) {
            try {
                System.loadLibrary("jniPtapi");
                isLibraryLoaded = true;
            } catch (UnsatisfiedLinkError e) {
                try {
                    System.out.println(e.toString());
                } catch (SecurityException e2) {
                    try {
                        System.out.println(e2.toString() + e2.getMessage());
                    } catch (RuntimeException e3) {
                        try {
                            System.out.println(e3.toString());
                        } catch (UnsatisfiedLinkError e4) {
                            System.out.println(e4.toString());
                        }
                    }
                }
            }
        }
    }

    public static synchronized void libraryIsAlreadyLoaded() {
        synchronized (PtGlobal.class) {
            isLibraryLoaded = true;
        }
    }
}
