package com.tdonuk.exception;

import com.tdonuk.constant.ErrorCode;

public class ConflictException extends BaseException {
    public ConflictException(String shortDes, String longDes) {
        super(ErrorCode.CONFLICT.getCode(), shortDes, longDes, 409);
    }
}
