package com.tdonuk.constant;

public enum ErrorCode {
    NOT_FOUND(100), CONFLICT(101), VALIDATION(102);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
