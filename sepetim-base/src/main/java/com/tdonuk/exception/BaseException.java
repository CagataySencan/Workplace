package com.tdonuk.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends Exception {
    protected int code;
    protected String shortDesc;
    protected String longDes;

    public BaseException(int code, String shortDes, String longDes) {
        super(shortDes);

        this.code = code;
        this.shortDesc = shortDes;
        this.longDes = longDes;
    }
}
