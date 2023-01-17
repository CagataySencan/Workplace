package com.tdonuk.dto.domain.vendor;

import com.tdonuk.constant.VendorType;
import com.tdonuk.dto.domain.BaseDTO;
import com.tdonuk.dto.domain.product.ProductDTO;
import lombok.Data;
import lombok.NonNull;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
public class VendorDTO extends BaseDTO {
    @NonNull
    private String name;
    @NonNull
    private String url;
    private ChronoUnit cycle;
    private Integer cyclePeriod;
    private VendorType type;
    private List<ProductDTO> actual;
}
