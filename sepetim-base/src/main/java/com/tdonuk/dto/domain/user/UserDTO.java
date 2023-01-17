package com.tdonuk.dto.domain.user;

import com.tdonuk.dto.domain.BaseDTO;
import com.tdonuk.dto.domain.vendor.VendorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String phone;
    private Name name;
    private Integer age;
    private List<String> interests;
    private List<VendorDTO> vendors;
}
