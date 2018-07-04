package com.upek.android.ptapi;

import android.support.v4.os.EnvironmentCompat;

public class PtException extends Exception {
    public static final int PT_STATUS_ACCESS_DENIED = -1046;
    public static final int PT_STATUS_ALREADY_OPENED = -1039;
    public static final int PT_STATUS_ANTISPOOFING_EXPORT = -1044;
    public static final int PT_STATUS_ANTISPOOFING_IMPORT = -1045;
    public static final int PT_STATUS_ANTISPOOFING_NOT_CAPTURED = -1065;
    public static final int PT_STATUS_API_ALREADY_INITIALIZED = -1003;
    public static final int PT_STATUS_API_NOT_INIT = -1002;
    public static final int PT_STATUS_AS_BG_OUT_OF_RANGE = -1132;
    public static final int PT_STATUS_AS_OFFSET_OUT_OF_RANGE = -1131;
    public static final int PT_STATUS_AUTHENTIFICATION_FAILED = -1053;
    public static final int PT_STATUS_BAD_BIO_TEMPLATE = -1042;
    public static final int PT_STATUS_BIOMETRIC_TIMEOUT = -1050;
    public static final int PT_STATUS_BIO_OPERATION_CANCELED = -1052;
    public static final int PT_STATUS_CALIBRATION_FAILED = -1064;
    public static final int PT_STATUS_CANCEL_IGNORED = -1116;
    public static final int PT_STATUS_CANNOT_CONNECT = -1040;
    public static final int PT_STATUS_COMM_ERROR = -1057;
    public static final int PT_STATUS_CONSOLIDATION_FAILED = -1051;
    public static final int PT_STATUS_CRYPTO_ERROR = -1090;
    public static final int PT_STATUS_CRYPTO_KEY_TOO_LONG = -1093;
    public static final int PT_STATUS_CRYPTO_MECHANISM_NOT_SUPPORTED = -1091;
    public static final int PT_STATUS_CRYPTO_PADDING_ERROR = -1092;
    public static final int PT_STATUS_CRYPTO_RSA_BAD_PUBLIC_KEY = -1101;
    public static final int PT_STATUS_CRYPTO_RSA_KEY_CHECK = -1100;
    public static final int PT_STATUS_CRYPTO_SYM_BAD_KEY = -1094;
    public static final int PT_STATUS_DATA_TOO_LARGE = -1008;
    public static final int PT_STATUS_DEVICE_NOT_FOUND = -1077;
    public static final int PT_STATUS_DEVICE_NOT_GRANTED = -1106;
    public static final int PT_STATUS_DEVICE_SICK = -1078;
    public static final int PT_STATUS_DIAGNOSTICS_FAILED = -1067;
    public static final int PT_STATUS_EMULATED_NVM_INVALID_FORMAT = -1118;
    public static final int PT_STATUS_ENDIAN_ERROR = -1107;
    public static final int PT_STATUS_EX_ACCESS_VIOLATION = -1096;
    public static final int PT_STATUS_FUNCTION_FAILED = -1033;
    public static final int PT_STATUS_GAIN_OFFSET = -1073;
    public static final int PT_STATUS_GENERAL_ERROR = -1001;
    public static final int PT_STATUS_GOING_TO_SLEEP = -1055;
    public static final int PT_STATUS_HW_RNG_INIT_ERROR = -1095;
    public static final int PT_STATUS_I2C_EEPROM_ERROR = -1060;
    public static final int PT_STATUS_INVALID_HANDLE = -1005;
    public static final int PT_STATUS_INVALID_INPUT_BIR_FORM = -1036;
    public static final int PT_STATUS_INVALID_OTP_SEED = -1110;
    public static final int PT_STATUS_INVALID_PARAMETER = -1004;
    public static final int PT_STATUS_INVALID_PURPOSE = -1061;
    public static final int PT_STATUS_JAVA_CB_ERROR = -1105;
    public static final int PT_STATUS_JAVA_CB_REMOTE = -1104;
    public static final int PT_STATUS_KEYEX_KEY_NOT_SET = -1108;
    public static final int PT_STATUS_LATCHUP_DETECTED = -1066;
    public static final int PT_STATUS_MALLOC_FAILED = -1007;
    public static final int PT_STATUS_MORE_DATA = -1010;
    public static final int PT_STATUS_NOT_ENOUGH_MEMORY = -1006;
    public static final int PT_STATUS_NOT_ENOUGH_PERMANENT_MEMORY = -1009;
    public static final int PT_STATUS_NOT_ENOUGH_TFM_MEMORY = -1038;
    public static final int PT_STATUS_NOT_IMPLEMENTED = -1056;
    public static final int PT_STATUS_NOT_SUPPORTED = -1063;
    public static final int PT_STATUS_NO_DATA = -1099;
    public static final int PT_STATUS_NO_LAST_MATCH_DATA = -1098;
    public static final int PT_STATUS_NO_SENSOR = -1069;
    public static final int PT_STATUS_NO_TEMPLATE = -1049;
    public static final int PT_STATUS_NVM_CANNOT_READ = -1088;
    public static final int PT_STATUS_NVM_CANNOT_WRITE = -1087;
    public static final int PT_STATUS_NVM_ERROR = -1086;
    public static final int PT_STATUS_NVM_INVALID_FILE_ID = -1089;
    public static final int PT_STATUS_NVM_INVALID_FORMAT = -1102;
    public static final int PT_STATUS_NVM_UNSUPPORTED_VERSION = -1103;
    public static final int PT_STATUS_OK = 0;
    public static final int PT_STATUS_OLD_VERSION = -1075;
    public static final int PT_STATUS_OPERATION_CANCELED = -1111;
    public static final int PT_STATUS_OS_ACCESS_DENIED = -1130;
    public static final int PT_STATUS_OTP_SEQUENCE_NUMBER_OVERFLOW = -1085;
    public static final int PT_STATUS_POWER_SHUTOFF = -1074;
    public static final int PT_STATUS_SAFE_MODE = -1081;
    public static final int PT_STATUS_SAME_VERSION = -1068;
    public static final int PT_STATUS_SC_BAD_PARAM = -3103;
    public static final int PT_STATUS_SC_COMM_FAIL = -3102;
    public static final int PT_STATUS_SC_ERROR = -3100;
    public static final int PT_STATUS_SC_NOT_SUPPORTED = -3101;
    public static final int PT_STATUS_SC_NO_CARD = -3104;
    public static final int PT_STATUS_SECCHAN_KEY_NOT_SET = -1114;
    public static final int PT_STATUS_SECURE_CHANNEL_ALREADY_ESTABLISHED = -1084;
    public static final int PT_STATUS_SENSOR_BUS_CONGESTION = -1117;
    public static final int PT_STATUS_SENSOR_HW_ERROR = -1082;
    public static final int PT_STATUS_SENSOR_NOT_CALIBRATED = -1080;
    public static final int PT_STATUS_SENSOR_NOT_REPAIRABLE = -1072;
    public static final int PT_STATUS_SENSOR_OUT_OF_LIMITS = -1070;
    public static final int PT_STATUS_SESSION_NOT_AUTHENTICATED = -1083;
    public static final int PT_STATUS_SESSION_TERMINATED = -1058;
    public static final int PT_STATUS_SIGN_KEY_NOT_SET = -1109;
    public static final int PT_STATUS_SLOT_NOT_FOUND = -1043;
    public static final int PT_STATUS_SONLY_ALREADY_OPENED = -1113;
    public static final int PT_STATUS_SUSPEND = -1076;
    public static final int PT_STATUS_SWIPE_TOO_BAD = -1062;
    public static final int PT_STATUS_TCS_OVERCURRENT = -1115;
    public static final int PT_STATUS_TIMEOUT = -1041;
    public static final int PT_STATUS_TOO_MANY_BAD_LINES = -1071;
    public static final int PT_STATUS_TOUCH_CHIP_ERROR = -1059;
    public static final int PT_STATUS_UNKNOWN_COMMAND = -1054;
    public static final int PT_STATUS_UNSUPPORTED_SPEED = -1079;
    public static final int PT_STATUS_VCH_ALREADY_REGISTERED = -1121;
    public static final int PT_STATUS_VCH_CHANNEL_NOT_AVAILABLE = -1129;
    public static final int PT_STATUS_VCH_COMM_ERROR = -1124;
    public static final int PT_STATUS_VCH_ERROR = -1119;
    public static final int PT_STATUS_VCH_INVALID_HANDLE = -1126;
    public static final int PT_STATUS_VCH_INVALID_IMPLEMENTATION = -1120;
    public static final int PT_STATUS_VCH_INVALID_PROTOCOL = -1128;
    public static final int PT_STATUS_VCH_JOB_ABORTED = -1127;
    public static final int PT_STATUS_VCH_LOST = -1112;
    public static final int PT_STATUS_VCH_MISSING_DEPENDENCIES = -1123;
    public static final int PT_STATUS_VCH_NOT_REGISTERED = -1122;
    public static final int PT_STATUS_VCH_TIMEOUT = -1125;
    public static final int PT_STATUS_WRONG_FINGER_DATA_ACCESS_RIGHTS = -1097;
    public static final int PT_STATUS_WRONG_RESPONSE = -1037;
    private static final long serialVersionUID = -3495268522431934546L;
    int code;

    public PtException(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        String result = "PtApi error(" + this.code + "): ";
        switch (this.code) {
            case PT_STATUS_SC_NO_CARD /*-3104*/:
                return new StringBuilder(String.valueOf(result)).append("The card is not present in the reader").toString();
            case PT_STATUS_SC_BAD_PARAM /*-3103*/:
                return new StringBuilder(String.valueOf(result)).append("Incorrect parameter detected").toString();
            case PT_STATUS_SC_COMM_FAIL /*-3102*/:
                return new StringBuilder(String.valueOf(result)).append("Failure during communication with the card").toString();
            case PT_STATUS_SC_NOT_SUPPORTED /*-3101*/:
                return new StringBuilder(String.valueOf(result)).append("Communication with this card is not supported").toString();
            case PT_STATUS_SC_ERROR /*-3100*/:
                return new StringBuilder(String.valueOf(result)).append("General smart-card error").toString();
            case PT_STATUS_SECCHAN_KEY_NOT_SET /*-1114*/:
                return new StringBuilder(String.valueOf(result)).append("Private SECCHAN key was not set").toString();
            case PT_STATUS_SONLY_ALREADY_OPENED /*-1113*/:
                return new StringBuilder(String.valueOf(result)).append("SONLY session is already opened").toString();
            case PT_STATUS_VCH_LOST /*-1112*/:
                return new StringBuilder(String.valueOf(result)).append("Virtual channel connection has been lost").toString();
            case PT_STATUS_OPERATION_CANCELED /*-1111*/:
                return new StringBuilder(String.valueOf(result)).append("Operation was canceled").toString();
            case PT_STATUS_INVALID_OTP_SEED /*-1110*/:
                return new StringBuilder(String.valueOf(result)).append("Invalid OTP seed").toString();
            case PT_STATUS_SIGN_KEY_NOT_SET /*-1109*/:
                return new StringBuilder(String.valueOf(result)).append("Private SIGN key was not set").toString();
            case PT_STATUS_KEYEX_KEY_NOT_SET /*-1108*/:
                return new StringBuilder(String.valueOf(result)).append("Private KEYEX key was not set").toString();
            case PT_STATUS_ENDIAN_ERROR /*-1107*/:
                return new StringBuilder(String.valueOf(result)).append("Can not convert some parameter to proper endian. This can happenonly if the paramater is pointer to memory whose meaning is contextdependent and if the host uses other then little endian.").toString();
            case PT_STATUS_DEVICE_NOT_GRANTED /*-1106*/:
                return new StringBuilder(String.valueOf(result)).append("Access to device not granted").toString();
            case PT_STATUS_JAVA_CB_ERROR /*-1105*/:
                return new StringBuilder(String.valueOf(result)).append("Java callback throws java.lang.Error").toString();
            case PT_STATUS_JAVA_CB_REMOTE /*-1104*/:
                return new StringBuilder(String.valueOf(result)).append("Java callback throws java.rmi.RemoteException").toString();
            case PT_STATUS_NVM_UNSUPPORTED_VERSION /*-1103*/:
                return new StringBuilder(String.valueOf(result)).append("NVM has unsupported version").toString();
            case PT_STATUS_NVM_INVALID_FORMAT /*-1102*/:
                return new StringBuilder(String.valueOf(result)).append("NVM has invalid format or is not formatted at all").toString();
            case PT_STATUS_CRYPTO_RSA_BAD_PUBLIC_KEY /*-1101*/:
                return new StringBuilder(String.valueOf(result)).append("Bad RSA public key supplied").toString();
            case PT_STATUS_CRYPTO_RSA_KEY_CHECK /*-1100*/:
                return new StringBuilder(String.valueOf(result)).append("RSA key is not consistent").toString();
            case PT_STATUS_NO_DATA /*-1099*/:
                return new StringBuilder(String.valueOf(result)).append("No data found").toString();
            case PT_STATUS_NO_LAST_MATCH_DATA /*-1098*/:
                return new StringBuilder(String.valueOf(result)).append("Last match data don't exist").toString();
            case PT_STATUS_WRONG_FINGER_DATA_ACCESS_RIGHTS /*-1097*/:
                return new StringBuilder(String.valueOf(result)).append("Used wrong finger data access rights").toString();
            case PT_STATUS_EX_ACCESS_VIOLATION /*-1096*/:
                return new StringBuilder(String.valueOf(result)).append("Device is already opened for exclusive access by somebody else").toString();
            case PT_STATUS_HW_RNG_INIT_ERROR /*-1095*/:
                return new StringBuilder(String.valueOf(result)).append("HW random number generator initialization failed").toString();
            case PT_STATUS_CRYPTO_SYM_BAD_KEY /*-1094*/:
                return new StringBuilder(String.valueOf(result)).append("Bad symmetric key used").toString();
            case PT_STATUS_CRYPTO_KEY_TOO_LONG /*-1093*/:
                return new StringBuilder(String.valueOf(result)).append("Key too long probably due to the export regulations;").toString();
            case PT_STATUS_CRYPTO_PADDING_ERROR /*-1092*/:
                return new StringBuilder(String.valueOf(result)).append("Padding error detected during crypto operation").toString();
            case PT_STATUS_CRYPTO_MECHANISM_NOT_SUPPORTED /*-1091*/:
                return new StringBuilder(String.valueOf(result)).append("Requested cryptographic mechanism is not supported").toString();
            case PT_STATUS_CRYPTO_ERROR /*-1090*/:
                return new StringBuilder(String.valueOf(result)).append("General crypto error").toString();
            case PT_STATUS_NVM_INVALID_FILE_ID /*-1089*/:
                return new StringBuilder(String.valueOf(result)).append("Attempt to access non-existing internal NVM file").toString();
            case PT_STATUS_NVM_CANNOT_READ /*-1088*/:
                return new StringBuilder(String.valueOf(result)).append("NVM read operation failed").toString();
            case PT_STATUS_NVM_CANNOT_WRITE /*-1087*/:
                return new StringBuilder(String.valueOf(result)).append("NVM write operation failed").toString();
            case PT_STATUS_NVM_ERROR /*-1086*/:
                return new StringBuilder(String.valueOf(result)).append("General NVM error").toString();
            case PT_STATUS_OTP_SEQUENCE_NUMBER_OVERFLOW /*-1085*/:
                return new StringBuilder(String.valueOf(result)).append("Overflow of One Time Password sequence number").toString();
            case PT_STATUS_SECURE_CHANNEL_ALREADY_ESTABLISHED /*-1084*/:
                return new StringBuilder(String.valueOf(result)).append("Secure channel has been already established").toString();
            case PT_STATUS_SESSION_NOT_AUTHENTICATED /*-1083*/:
                return new StringBuilder(String.valueOf(result)).append("Session was not authenticated yet").toString();
            case PT_STATUS_SENSOR_HW_ERROR /*-1082*/:
                return new StringBuilder(String.valueOf(result)).append("Sensor hardware error occured").toString();
            case PT_STATUS_SAFE_MODE /*-1081*/:
                return new StringBuilder(String.valueOf(result)).append("Firmware is missing or corrupted, device is running in safe mode").toString();
            case PT_STATUS_SENSOR_NOT_CALIBRATED /*-1080*/:
                return new StringBuilder(String.valueOf(result)).append("Sensor is not calibrated").toString();
            case PT_STATUS_UNSUPPORTED_SPEED /*-1079*/:
                return new StringBuilder(String.valueOf(result)).append("Host hardware doesn't support requested communication speed").toString();
            case PT_STATUS_DEVICE_SICK /*-1078*/:
                return new StringBuilder(String.valueOf(result)).append("Device doesn't work as expected").toString();
            case PT_STATUS_DEVICE_NOT_FOUND /*-1077*/:
                return new StringBuilder(String.valueOf(result)).append("Device not found").toString();
            case PT_STATUS_SUSPEND /*-1076*/:
                return new StringBuilder(String.valueOf(result)).append("Connection interrupted because of suspend request").toString();
            case PT_STATUS_OLD_VERSION /*-1075*/:
                return new StringBuilder(String.valueOf(result)).append("Attempt to upgrade to older firmware version").toString();
            case PT_STATUS_POWER_SHUTOFF /*-1074*/:
                return new StringBuilder(String.valueOf(result)).append("Asynchronous power shut down").toString();
            case PT_STATUS_GAIN_OFFSET /*-1073*/:
                return new StringBuilder(String.valueOf(result)).append("Gain offset calibration error").toString();
            case PT_STATUS_SENSOR_NOT_REPAIRABLE /*-1072*/:
                return new StringBuilder(String.valueOf(result)).append("Sensor is not repairable").toString();
            case PT_STATUS_TOO_MANY_BAD_LINES /*-1071*/:
                return new StringBuilder(String.valueOf(result)).append("Too many bad lines").toString();
            case PT_STATUS_SENSOR_OUT_OF_LIMITS /*-1070*/:
                return new StringBuilder(String.valueOf(result)).append("The measured values are out of allowable limits").toString();
            case PT_STATUS_NO_SENSOR /*-1069*/:
                return new StringBuilder(String.valueOf(result)).append("No sensor").toString();
            case PT_STATUS_SAME_VERSION /*-1068*/:
                return new StringBuilder(String.valueOf(result)).append("Attempt to upgrade to same firmware version").toString();
            case PT_STATUS_DIAGNOSTICS_FAILED /*-1067*/:
                return new StringBuilder(String.valueOf(result)).append("Diagnostics failed").toString();
            case PT_STATUS_LATCHUP_DETECTED /*-1066*/:
                return new StringBuilder(String.valueOf(result)).append("Sensor latch-up event detected").toString();
            case PT_STATUS_ANTISPOOFING_NOT_CAPTURED /*-1065*/:
                return new StringBuilder(String.valueOf(result)).append("Antispoofing data were not captured").toString();
            case PT_STATUS_CALIBRATION_FAILED /*-1064*/:
                return new StringBuilder(String.valueOf(result)).append("Calibration failed").toString();
            case PT_STATUS_NOT_SUPPORTED /*-1063*/:
                return new StringBuilder(String.valueOf(result)).append("Requested functionality or value of parameter is not supported").toString();
            case PT_STATUS_SWIPE_TOO_BAD /*-1062*/:
                return new StringBuilder(String.valueOf(result)).append("Finger swipe is too bad for image reconstruction").toString();
            case PT_STATUS_INVALID_PURPOSE /*-1061*/:
                return new StringBuilder(String.valueOf(result)).append("Purpose parameter or BIR's purpose; is invalid for given operation").toString();
            case PT_STATUS_I2C_EEPROM_ERROR /*-1060*/:
                return new StringBuilder(String.valueOf(result)).append("I2C EEPROM error occured").toString();
            case PT_STATUS_TOUCH_CHIP_ERROR /*-1059*/:
                return new StringBuilder(String.valueOf(result)).append("Touch chip error occured").toString();
            case PT_STATUS_SESSION_TERMINATED /*-1058*/:
                return new StringBuilder(String.valueOf(result)).append("Session was terminated").toString();
            case PT_STATUS_COMM_ERROR /*-1057*/:
                return new StringBuilder(String.valueOf(result)).append("General communication error").toString();
            case PT_STATUS_NOT_IMPLEMENTED /*-1056*/:
                return new StringBuilder(String.valueOf(result)).append("Function or service is not implemented").toString();
            case PT_STATUS_GOING_TO_SLEEP /*-1055*/:
                return new StringBuilder(String.valueOf(result)).append("Power off attempt failed").toString();
            case PT_STATUS_UNKNOWN_COMMAND /*-1054*/:
                return new StringBuilder(String.valueOf(result)).append("Unknown command").toString();
            case PT_STATUS_AUTHENTIFICATION_FAILED /*-1053*/:
                return new StringBuilder(String.valueOf(result)).append("Authentification failed").toString();
            case PT_STATUS_BIO_OPERATION_CANCELED /*-1052*/:
                return new StringBuilder(String.valueOf(result)).append("Biometric operation canceled").toString();
            case PT_STATUS_CONSOLIDATION_FAILED /*-1051*/:
                return new StringBuilder(String.valueOf(result)).append("Failure of template consolidation").toString();
            case PT_STATUS_BIOMETRIC_TIMEOUT /*-1050*/:
                return new StringBuilder(String.valueOf(result)).append("Timeout for biometric operation has expired").toString();
            case PT_STATUS_NO_TEMPLATE /*-1049*/:
                return new StringBuilder(String.valueOf(result)).append("No template was captured in current session").toString();
            case PT_STATUS_ACCESS_DENIED /*-1046*/:
                return new StringBuilder(String.valueOf(result)).append("Access to operation is denied").toString();
            case PT_STATUS_ANTISPOOFING_IMPORT /*-1045*/:
                return new StringBuilder(String.valueOf(result)).append("Attempt to import antispoofing info to TFM").toString();
            case PT_STATUS_ANTISPOOFING_EXPORT /*-1044*/:
                return new StringBuilder(String.valueOf(result)).append("Attempt to export antispoofing info from TFM").toString();
            case PT_STATUS_SLOT_NOT_FOUND /*-1043*/:
                return new StringBuilder(String.valueOf(result)).append("Requested slot was not found").toString();
            case PT_STATUS_BAD_BIO_TEMPLATE /*-1042*/:
                return new StringBuilder(String.valueOf(result)).append("Bad biometric template").toString();
            case PT_STATUS_TIMEOUT /*-1041*/:
                return new StringBuilder(String.valueOf(result)).append("Timeout elapsed").toString();
            case PT_STATUS_CANNOT_CONNECT /*-1040*/:
                return new StringBuilder(String.valueOf(result)).append("Cannot connect to TFM").toString();
            case PT_STATUS_ALREADY_OPENED /*-1039*/:
                return new StringBuilder(String.valueOf(result)).append("Connection is already opened").toString();
            case PT_STATUS_NOT_ENOUGH_TFM_MEMORY /*-1038*/:
                return new StringBuilder(String.valueOf(result)).append("Not enough memory on TFM to process given operation").toString();
            case PT_STATUS_WRONG_RESPONSE /*-1037*/:
                return new StringBuilder(String.valueOf(result)).append("TFM has returned wrong or unexpected response").toString();
            case PT_STATUS_INVALID_INPUT_BIR_FORM /*-1036*/:
                return new StringBuilder(String.valueOf(result)).append("Invalid form of PT_INPUT_BIR structure").toString();
            case PT_STATUS_FUNCTION_FAILED /*-1033*/:
                return new StringBuilder(String.valueOf(result)).append("Function failed").toString();
            case PT_STATUS_MORE_DATA /*-1010*/:
                return new StringBuilder(String.valueOf(result)).append("There is more data to return than the supplied buffer can contain").toString();
            case PT_STATUS_NOT_ENOUGH_PERMANENT_MEMORY /*-1009*/:
                return new StringBuilder(String.valueOf(result)).append("Not enough permanent memory to store data").toString();
            case PT_STATUS_DATA_TOO_LARGE /*-1008*/:
                return new StringBuilder(String.valueOf(result)).append("Passed data are too large").toString();
            case PT_STATUS_MALLOC_FAILED /*-1007*/:
                return new StringBuilder(String.valueOf(result)).append("Failure of extern memory allocation function").toString();
            case PT_STATUS_NOT_ENOUGH_MEMORY /*-1006*/:
                return new StringBuilder(String.valueOf(result)).append("Not enough memory to process given operation").toString();
            case PT_STATUS_INVALID_HANDLE /*-1005*/:
                return new StringBuilder(String.valueOf(result)).append("Invalid handle error").toString();
            case PT_STATUS_INVALID_PARAMETER /*-1004*/:
                return new StringBuilder(String.valueOf(result)).append("Invalid parameter error").toString();
            case PT_STATUS_API_ALREADY_INITIALIZED /*-1003*/:
                return new StringBuilder(String.valueOf(result)).append("PerfectTrust API has been already initialized").toString();
            case PT_STATUS_API_NOT_INIT /*-1002*/:
                return new StringBuilder(String.valueOf(result)).append("PerfectTrust API wasn't initialized").toString();
            case PT_STATUS_GENERAL_ERROR /*-1001*/:
                return new StringBuilder(String.valueOf(result)).append("General or unknown error status. It is also possible that the function only partially succeeded, and that the device is in an inconsistent state.").toString();
            case 0:
                return new StringBuilder(String.valueOf(result)).append("Success return status").toString();
            default:
                return new StringBuilder(String.valueOf(result)).append(EnvironmentCompat.MEDIA_UNKNOWN).toString();
        }
    }
}
