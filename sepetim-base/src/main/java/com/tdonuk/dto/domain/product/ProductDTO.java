package com.tdonuk.dto.domain.product;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.BaseDTO;
import com.tdonuk.dto.domain.DomainDTO;
import com.tdonuk.dto.domain.vendor.VendorDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO extends BaseDTO {
    private Vendor vendor;
    private String brand;
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private Double oldPrice;
    private Double price;
    private Date discountEndDate;
    private Boolean inDiscount;
    private Boolean inActual;
}
