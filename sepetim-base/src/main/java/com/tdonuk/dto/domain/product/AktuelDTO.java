package com.tdonuk.dto.domain.product;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.BaseDTO;
import com.tdonuk.util.text.StringUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class AktuelDTO extends BaseDTO implements Comparable<AktuelDTO> {
    private Vendor vendor;
    private Date date;
    private List<String> bannerPageLinks;

    @Override
    public int compareTo(AktuelDTO o) {
        return getDate().compareTo(o.getDate());
    }


    public boolean isEquals(AktuelDTO other) {
        if(Objects.isNull(other)) return false;
        if(StringUtils.isBlank(this.id) || StringUtils.isBlank(other.id)) return false;

        return id.equals(other.getId());
    }

}
