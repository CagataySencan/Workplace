package com.tdonuk.dto.domain.vendor;

import com.tdonuk.constant.VendorType;
import com.tdonuk.dto.BaseDTO;
import com.tdonuk.dto.domain.product.DiscountDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class VendorDTO extends BaseDTO {
    @NonNull
    private String name;
    @NonNull
    private String url;
    private VendorType type;
    private List<DiscountDTO> discountHist;
}
