package com.tdonuk.dto.domain.user;

import com.tdonuk.dto.domain.DomainDTO;
import com.tdonuk.dto.domain.vendor.VendorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO extends DomainDTO {
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String phone;
    private Name name;
    private Date age;
    private List<String> interests;
    private List<VendorDTO> vendors;
    private Date lastLogin;
}
