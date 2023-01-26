package com.tdonuk.sepetim.util;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.DiscountDTO;
import com.tdonuk.exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DiscountUtils {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
    public static String generateId(Vendor vendor, Date date) throws ValidationException {
        if(Objects.isNull(vendor) || Objects.isNull(date)) throw new ValidationException("Vendor or date is null", "Can not generate discount id: vendor or date is null");

        return vendor.name() + formatter.format(date);
    }

    public static void generateId(DiscountDTO discount) throws ValidationException {
        String id = generateId(discount.getVendor(), discount.getBeginDate());
        discount.setId(id);
    }
}
