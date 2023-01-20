package com.tdonuk.dto.domain;

import com.tdonuk.dto.BaseDTO;
import com.tdonuk.dto.domain.vendor.VendorDTO;
import lombok.Data;

import java.util.List;

@Data
public class CurrentActualDTO extends BaseDTO {
    private Integer vendorCount;
    private Integer productCount;
    private List<VendorDTO> vendors;
}
