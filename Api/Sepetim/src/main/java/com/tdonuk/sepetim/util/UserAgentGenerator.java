package com.tdonuk.sepetim.util;

import com.tdonuk.util.BaseUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserAgentGenerator extends BaseUtils {
    private static List<String> userAgents = Arrays.asList(
            "Windows 10/ Edge browser: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)",
            "Chrome/42.0.2311.135 Safari/537.36 Edge/12.246",
            "Windows 7/ Chrome browser: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)",
            "Linux PC/Firefox browser: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:15.0) Gecko/20100101 Firefox/15.0.1",
            "Chrome OS/Chrome browser: Mozilla/5.0 (X11; CrOS x86_64 8172.45.0) AppleWebKit/537.36 (KHTML, like Gecko)"
    );

    public static String generateRandom() {
        return userAgents.get(random.nextInt(0, userAgents.size()-1));
    }
}
