package com.tdonuk.dto.domain.product;

import com.tdonuk.dto.domain.vendor.VendorDTO;
import lombok.Data;

import java.util.List;

@Data
public class CurrentActualDTO {
    private Integer vendorCount;
    private Integer productCount;
    private List<VendorDTO> vendors;
}
