package com.tdonuk.exception;

import com.tdonuk.constant.ErrorCode;

public class ValidationException extends BaseException {
    public ValidationException(String shortDes, String longDes) {
        super(ErrorCode.VALIDATION.getCode(), shortDes, longDes, 400);
    }
}
