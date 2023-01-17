package com.tdonuk.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Logger;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseUtils {
    // Common utility tools
    protected static Logger logger = Logger.getGlobal();

    protected static Base64.Encoder encoder = Base64.getEncoder();

    protected static Base64.Decoder decoder = Base64.getDecoder();

    protected static Base64.Encoder urlEncoder = Base64.getUrlEncoder();

    protected static Base64.Decoder urlDecoder = Base64.getUrlDecoder();

    protected static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); // format can change

    protected static DecimalFormat numberFormatter = new DecimalFormat("##.00"); // format can change

    protected static Random random = new Random();
}
