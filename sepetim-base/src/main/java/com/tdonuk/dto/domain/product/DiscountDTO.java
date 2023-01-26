package com.tdonuk.dto.domain.product;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.BaseDTO;
import com.tdonuk.util.text.StringUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class DiscountDTO extends BaseDTO implements Comparable<DiscountDTO> {
    private Vendor vendor;
    private Date beginDate;
    private Date endDate;
    private List<String> bannerPageLinks;
    private List<ProductDTO> products;

    @Override
    public int compareTo(DiscountDTO o) {
        return getBeginDate().compareTo(o.getBeginDate());
    }


    public boolean isEquals(DiscountDTO other) {
        if(Objects.isNull(other)) return false;
        if(StringUtils.isBlank(this.id) || StringUtils.isBlank(other.id)) return false;

        return id.equals(other.getId());
    }

}
