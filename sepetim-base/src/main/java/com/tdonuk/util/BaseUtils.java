package com.tdonuk.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Pattern;

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

    public static Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z.]{2,7}$");
    public static Pattern phonePattern = Pattern.compile("^0\\d{10} || \\d{10}$");
    public static Pattern passwordPattern = Pattern.compile("^(?=.{6,20})(?=.*[A-Z])(?=.*[0-9])(?=.*[-.,_])[A-Za-z0-9-.,_]*$");
    public static Pattern namePattern = Pattern.compile("^(?=.{1,30})(?=\\S)(?=.*[a-zA-Z\\d].*[a-zA-Z\\d])[a-zA-Z\\s]*$");
}
