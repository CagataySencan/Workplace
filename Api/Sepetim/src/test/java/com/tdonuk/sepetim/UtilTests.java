package com.tdonuk.sepetim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilTests {
    @Test
    void phoneMatcherMatches() {
        String phone = "01111111111";

        Assertions.assertTrue(phone.matches("^0\\d{10}||\\d{10}$"));
    }
}
