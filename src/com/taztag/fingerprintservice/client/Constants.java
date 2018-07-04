package com.taztag.fingerprintservice.client;

public class Constants {
    public static final int FP1 = 1;
    public static final int FP2 = 2;
    public static final int FP3 = 3;
    public static final int FP4 = 4;
    public static final int NONE = -1;
    public static final String PREF_FILE = "myprefs";
    public static final String PREF_KEY_QUALITY = "quality";
    public static final int QUALITY_HIGH = 2;
    public static final int QUALITY_LOW = 0;
    public static final int QUALITY_MEDIUM = 1;

    public enum DIALOG_TYPE {
        ERROR,
        PROGRESS_SPINNER,
        RESULT_OK,
        RESULT_NOK,
        PROGRESS_HORIZONTAL
    }
}
