package com.tdonuk.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Time {
    public static final long SECOND = 1000;
    public static final long MINUTE = SECOND * 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;
    public static final long WEEK = DAY * 7;
    public static final long MONTH = WEEK * 4;
    public static final long YEAR = MONTH * 12;

    public static long seconds(int amount) {
        return SECOND * amount;
    }
    public static long minutes(int amount) {
        return MINUTE * amount;
    }
    public static long hours(int amount) {
        return HOUR * amount;
    }
    public static long days(int amount) {
        return DAY * amount;
    }
    public static long weeks(int amount) {
        return WEEK * amount;
    }
}
