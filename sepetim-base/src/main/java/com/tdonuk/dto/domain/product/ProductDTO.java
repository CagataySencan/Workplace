package com.tdonuk.dto.domain.product;

import com.tdonuk.dto.domain.BaseDTO;
import lombok.Data;

@Data
public class ProductDTO extends BaseDTO {
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private Double oldPrice;
    private Double newPrice;
    private Integer leftDaysInDiscount;
    private Boolean inDiscount;
    private Boolean inActual;

}
