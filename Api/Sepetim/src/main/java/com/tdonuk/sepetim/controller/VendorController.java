package com.tdonuk.sepetim.controller;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.vendor.VendorDTO;
import com.tdonuk.dto.http.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    @GetMapping(path = {"/", "/supported"})
    public ResponseEntity<?> getSupportedVendors() {
        List<VendorDTO> result = new ArrayList<>();

        VendorDTO vendorDTO;
        for(Vendor vendor : Vendor.values()) {
            vendorDTO = VendorDTO.builder()
                    .name(vendor.getTitle())
                    .url(vendor.getRootPath())
                    .type(vendor.getType())
                    .build();

            result.add(vendorDTO);
        }

        return ResponseEntity.ok(BaseResponse.of(result));
    }
}
