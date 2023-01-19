package com.tdonuk.util.text;

import com.tdonuk.util.BaseUtils;

import java.util.Objects;

/**
 * String utils
 */
public final class StringUtils extends BaseUtils {
    public static Boolean isBlank(final String s) {
        return Objects.isNull(s) || "".equals(s) || " ".equals(s);
    }
}
