package com.tdonuk.exception;

import com.tdonuk.constant.ErrorCode;

public class BadRequestException extends BaseException {
    public BadRequestException(String shortDesc, String longDes) {
        super(ErrorCode.USER_FAULT.getCode(), shortDesc, longDes, 400);
    }
}
