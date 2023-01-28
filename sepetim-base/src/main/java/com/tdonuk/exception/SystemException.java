package com.tdonuk.exception;

import com.tdonuk.constant.ErrorCode;

public class SystemException extends BaseException{
    public SystemException(String shortDesc, String longDes) {
        super(ErrorCode.SYSTEM_FAULT.getCode(), shortDesc, longDes, 400);
    }
}
