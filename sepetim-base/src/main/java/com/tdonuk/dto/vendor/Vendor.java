package com.tdonuk.dto.vendor;

import com.tdonuk.constant.VendorType;
import com.tdonuk.dto.BaseDTO;
import lombok.Data;
import lombok.NonNull;

import java.time.temporal.ChronoUnit;

@Data
public class Vendor extends BaseDTO {
    @NonNull
    private String name;
    @NonNull
    private String url;
    private ChronoUnit cycle;
    private Integer cyclePeriod;
    private VendorType type;
}
