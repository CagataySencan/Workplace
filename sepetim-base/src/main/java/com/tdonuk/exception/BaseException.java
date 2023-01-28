package com.tdonuk.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends Exception {
    protected int code;
    protected int status;
    protected String shortDesc;
    protected String longDes;

    public BaseException(int code, String shortDes, String longDes, int status) {
        super(shortDes);

        this.code = code;
        this.shortDesc = shortDes;
        this.longDes = longDes;
        this.status = status;
    }
}
