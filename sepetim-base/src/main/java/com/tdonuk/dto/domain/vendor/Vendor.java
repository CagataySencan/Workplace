package com.tdonuk.dto.domain.vendor;

import com.tdonuk.constant.VendorType;
import com.tdonuk.dto.domain.BaseDTO;
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
