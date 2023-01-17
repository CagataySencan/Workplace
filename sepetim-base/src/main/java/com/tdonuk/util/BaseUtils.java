package com.tdonuk.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.logging.Logger;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseUtils {
    // Common utility tools
    protected Logger logger = Logger.getGlobal();

    protected Base64.Encoder encoder = Base64.getEncoder();

    protected Base64.Decoder decoder = Base64.getDecoder();

    protected Base64.Encoder urlEncoder = Base64.getUrlEncoder();

    protected Base64.Decoder urlDecoder = Base64.getUrlDecoder();

    protected SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); // format can change

    protected DecimalFormat numberFormatter = new DecimalFormat("##.00"); // format can change
}
