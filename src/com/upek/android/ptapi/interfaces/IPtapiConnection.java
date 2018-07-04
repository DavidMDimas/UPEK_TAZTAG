package com.upek.android.ptapi.interfaces;

import com.upek.android.ptapi.PtConnectionAdvancedI;

public interface IPtapiConnection {
    boolean close();

    int errorCode();

    PtConnectionAdvancedI open();
}
