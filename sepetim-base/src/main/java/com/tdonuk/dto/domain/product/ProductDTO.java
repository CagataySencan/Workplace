package com.tdonuk.dto.domain.product;

import com.tdonuk.dto.BaseDTO;
import com.tdonuk.dto.domain.DomainDTO;
import com.tdonuk.dto.domain.vendor.VendorDTO;
import lombok.Data;

@Data
public class ProductDTO extends BaseDTO {
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private VendorDTO vendor;
    private Double oldPrice;
    private Double price;
    private Integer leftDaysInDiscount;
    private Boolean inDiscount;
    private Boolean inActual;

}
