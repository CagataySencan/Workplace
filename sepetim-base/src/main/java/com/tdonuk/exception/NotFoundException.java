package com.tdonuk.exception;

import com.tdonuk.constant.ErrorCode;

public class NotFoundException extends BaseException {
    public NotFoundException(String shortDes, String longDes) {
        super(ErrorCode.NOT_FOUND.getCode(), shortDes, longDes);
    }
}
