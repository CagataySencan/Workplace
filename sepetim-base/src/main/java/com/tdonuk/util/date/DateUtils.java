package com.tdonuk.util.date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DateFormatSymbols;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {
    public static final DateFormatSymbols DATE_SYMBOLS = new DateFormatSymbols(new Locale("tr", "TR"));
}
