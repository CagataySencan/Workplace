package com.tdonuk.util.io;

import com.tdonuk.util.BaseUtils;

import java.nio.charset.StandardCharsets;

/**
 * ZIP/Unzip files, file transformations (byte[] to file, file to byte[]), Base64 utils, etc..
 */

public class IO extends BaseUtils {
    public static String encode(final String s) {
        return encoder.encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(final String s) {
        return new String(decoder.decode(s));
    }

    public static String decode(final byte[] source) {
        return new String(decoder.decode(source));
    }

    public static String encode(final byte[] source) {
        return new String(encoder.encode(source));
    }

    public static byte[] decodeToBytes(final String s) {
        return decoder.decode(s);
    }

    public static byte[] encodeToBytes(final String s) {
        return encoder.encode(s.getBytes(StandardCharsets.UTF_8));
    }
}
