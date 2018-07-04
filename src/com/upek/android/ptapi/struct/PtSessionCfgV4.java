package com.upek.android.ptapi.struct;

import java.io.Serializable;

public class PtSessionCfgV4 implements Serializable, Cloneable {
    private static final long serialVersionUID = -3529343640035797373L;
    public short antispoofingEnable;
    public short antispoofingSecurityLevel;
    public short bioEnrollInputType;
    public short bioFingerDetectInputType;
    public short bioVerifyInputType;
    public int callbackLevel;
    public byte consolidationType;
    public byte createWrappedBIRs;
    public short enableScanQualityQuery;
    public byte enrollMaxMinu;
    public byte extractionMaxMinu;
    public byte identificationThreshold;
    public boolean latentDetect;
    public boolean multipleEnroll;
    public int navClickAllowedMovement;
    public short navClickTimeMax;
    public short navClickTimeMin;
    public short navFingerDownDelay;
    public short navFingerUpDelay;
    public short navFlags;
    public short navMovementDelay;
    public short navNavigationType;
    public byte navOrientation;
    public boolean navSubPixelPrecision;
    public boolean navUseHwDetection;
    public int recNoMovementResetTimeout;
    public int recNoMovementTimeout;
    public byte recNoiseRobustness;
    public boolean recRemoveBottomup;
    public boolean recRemoveTopdown;
    public byte recSwipeDirection;
    public int recSwipeTimeout;
    public short recTerminationPolicy;
    public boolean recUseHwDetection;
    public byte[] reserved1 = new byte[2];
    public byte[] reserved2 = new byte[2];
    public byte[] reserved4 = new byte[1];
    public byte[] reserved5 = new byte[17];
    public boolean restartBioTimeout;
    public byte retuning;
    public int securitySetting;
    public boolean sensorDisabling;
    public byte sensorSecurityMode;
    public byte signatureType;
    public boolean subSample;
    public byte subWindowArea;
    public byte suspendInSleep;
    public byte templateType;
    public boolean useHwFingerDetection;
    public boolean wakeUpByFinger;
    public int wakeUpByFingerTimeout;
    public short wffFingerDownDelay;
    public short wffFingerUpDelay;
    public boolean wffUseHwDetection;

    public PtSessionCfgV4 clone() throws CloneNotSupportedException {
        int i;
        PtSessionCfgV4 copy = (PtSessionCfgV4) super.clone();
        copy.reserved1 = new byte[2];
        for (i = 0; i < 2; i++) {
            copy.reserved1[i] = this.reserved1[i];
        }
        copy.reserved2 = new byte[2];
        for (i = 0; i < 2; i++) {
            copy.reserved2[i] = this.reserved2[i];
        }
        copy.reserved4 = new byte[1];
        for (i = 0; i < 1; i++) {
            copy.reserved4[i] = this.reserved4[i];
        }
        copy.reserved5 = new byte[17];
        for (i = 0; i < 17; i++) {
            copy.reserved5[i] = this.reserved5[i];
        }
        return copy;
    }
}
