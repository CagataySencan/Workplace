package com.tdonuk.dto.domain.product;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.BaseDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AktuelDTO extends BaseDTO implements Comparable<AktuelDTO> {
    private Vendor vendor;
    private Date date;
    private List<String> bannerPageLinks;

    @Override
    public int compareTo(AktuelDTO o) {
        return getDate().compareTo(o.getDate());
    }
}
