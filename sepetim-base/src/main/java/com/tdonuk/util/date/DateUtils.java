package com.tdonuk.util.date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {
    public static final DateFormatSymbols DATE_SYMBOLS = new DateFormatSymbols(new Locale("tr", "TR"));

    public static LocalDate dateToLocalDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDate minusDays(int days) {
        return LocalDate.now().minusDays(days);
    }
}
