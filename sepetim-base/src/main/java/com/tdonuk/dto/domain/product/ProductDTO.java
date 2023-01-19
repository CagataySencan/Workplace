package com.tdonuk.dto.domain.product;

import com.tdonuk.dto.domain.DomainDTO;
import com.tdonuk.dto.domain.vendor.VendorDTO;
import lombok.Data;

@Data
public class ProductDTO extends DomainDTO {
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private VendorDTO vendor;
    private Double oldPrice;
    private Double newPrice;
    private Integer leftDaysInDiscount;
    private Boolean inDiscount;
    private Boolean inActual;

}
