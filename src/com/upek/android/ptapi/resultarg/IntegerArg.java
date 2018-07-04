package com.upek.android.ptapi.resultarg;

public class IntegerArg implements IntegerArgI {
    private static final long serialVersionUID = 1679424234770319090L;
    public int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
