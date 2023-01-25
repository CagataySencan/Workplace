package com.tdonuk.sepetim.util;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.AktuelDTO;
import com.tdonuk.exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AktuelUtils {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
    public static String generateId(Vendor vendor, Date date) throws ValidationException {
        if(Objects.isNull(vendor) || Objects.isNull(date)) throw new ValidationException("Vendor or date is null", "Can not generate aktuel id: vendor or date is null");

        return vendor.name() + formatter.format(date);
    }

    public static void generateId(AktuelDTO aktuel) throws ValidationException {
        String id = generateId(aktuel.getVendor(), aktuel.getDate());
        aktuel.setId(id);
    }
}
