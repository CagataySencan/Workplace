package com.tdonuk.util.text;

import com.tdonuk.util.BaseUtils;

import java.util.Objects;

/**
 * String utils
 */
public final class StringUtils extends BaseUtils {
    public static boolean isBlank(final String s) {
        return Objects.isNull(s) || "".equals(s) || " ".equals(s);
    }

    public static boolean isNotBlank(final String s) {
        return !isBlank(s);
    }
}
