package com.tdonuk.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception {
    protected int code;
    protected String shortDesc;
    protected String longDes;
}
