package com.tdonuk.sepetim;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.DiscountDTO;
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
        DiscountDTO discount1 = new DiscountDTO();
        discount1.setBeginDate(new Date());
        discount1.setVendor(Vendor.BIM);

        DiscountDTO discount2 = new DiscountDTO();
        discount2.setBeginDate(discount1.getBeginDate());
        discount2.setVendor(Vendor.BIM);

        List<DiscountDTO> list = List.of(discount2);

        System.out.println(list.contains(discount1));
    }
}
