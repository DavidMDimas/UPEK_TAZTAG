package com.upek.android.ptapi.struct;

import java.io.Serializable;

public class PtSessionCfgV5 implements Serializable, Cloneable {
    private static final long serialVersionUID = -9102016327444649753L;
    public byte antispoofingEnable;
    public byte antispoofingRejectsEnrollment;
    public byte antispoofingSecurityLevel;
    public byte bandwidth;
    public byte bioEnrollInputType;
    public byte bioFingerDetectInputType;
    public byte bioVerifyInputType;
    public int callbackLevel;
    public byte consolidationNumTemplates;
    public byte consolidationSecurityLevel;
    public byte consolidationType;
    public byte createWrappedBIRs;
    public short enableScanQualityQuery;
    public byte enrollMaxMinu;
    public short enrollMaxSize;
    public byte enrollMaxTemplates;
    public byte enrollMinTemplates;
    public byte extractionMaxMinu;
    public short extractionMaxSize;
    public int fdetFlags;
    public byte identificationThreshold;
    public byte imageFormatPolicy;
    public byte latentDetect;
    public byte multipleEnroll;
    public int navClickAllowedMovement;
    public byte navClickSensitivity;
    public short navClickTimeMax;
    public short navClickTimeMin;
    public short navFingerDownDelay;
    public short navFingerUpDelay;
    public short navFlags;
    public short navMovementDelay;
    public byte navNavigationType;
    public byte navOrientation;
    public short powerFlags;
    public byte readerSecurityMode;
    public int recFlags;
    public int recNoMovementResetTimeout;
    public int recNoMovementTimeout;
    public byte recNoiseRobustness;
    public byte recNoiseRobustnessTrigger;
    public byte recSwipeDirection;
    public int recSwipeTimeout;
    public byte recTerminationPolicy;
    public byte[] reserved1 = new byte[4];
    public byte[] reserved2 = new byte[8];
    public byte[] reserved3 = new byte[9];
    public byte[] reserved4 = new byte[14];
    public byte restartBioTimeout;
    public byte retuning;
    public byte securitySetting;
    public byte sensorDisabling;
    public byte sensorSecurityMode;
    public byte signatureType;
    public byte suspendInSleep;
    public byte templateType;
    public byte useHwFingerDetection;
    public byte wakeUpByFinger;
    public int wakeUpByFingerTimeout;
    public short wffFingerDownDelay;
    public short wffFingerUpDelay;

    public PtSessionCfgV5 clone() throws CloneNotSupportedException {
        int i;
        PtSessionCfgV5 copy = (PtSessionCfgV5) super.clone();
        copy.reserved1 = new byte[4];
        for (i = 0; i < 4; i++) {
            copy.reserved1[i] = this.reserved1[i];
        }
        copy.reserved2 = new byte[8];
        for (i = 0; i < 8; i++) {
            copy.reserved2[i] = this.reserved2[i];
        }
        copy.reserved3 = new byte[9];
        for (i = 0; i < 9; i++) {
            copy.reserved3[i] = this.reserved3[i];
        }
        copy.reserved4 = new byte[14];
        for (i = 0; i < 14; i++) {
            copy.reserved4[i] = this.reserved4[i];
        }
        return copy;
    }
}
