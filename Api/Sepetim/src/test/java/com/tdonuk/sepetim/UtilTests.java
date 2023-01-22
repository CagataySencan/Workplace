package com.tdonuk.sepetim;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.AktuelDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class UtilTests {
    @Test
    void phoneMatcherMatches() {
        String phone = "01111111111";

        Assertions.assertTrue(phone.matches("^0\\d{10}||\\d{10}$"));
    }

    @Test
    void equalityTest() {
        AktuelDTO aktuel1 = new AktuelDTO();
        aktuel1.setDate(new Date());
        aktuel1.setVendor(Vendor.BIM);

        AktuelDTO aktuel2 = new AktuelDTO();
        aktuel2.setDate(aktuel1.getDate());
        aktuel2.setVendor(Vendor.BIM);

        List<AktuelDTO> list = List.of(aktuel2);

        System.out.println(list.contains(aktuel1));
    }
}
