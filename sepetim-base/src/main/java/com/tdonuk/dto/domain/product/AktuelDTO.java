package com.tdonuk.dto.domain.product;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.BaseDTO;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AktuelDTO aktuelDTO = (AktuelDTO) o;
        return vendor == aktuelDTO.vendor && date.equals(aktuelDTO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vendor, date);
    }
}
