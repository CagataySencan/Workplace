package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.service.AktuelService;
import com.tdonuk.sepetim.util.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aktuel")
public class AktuelController {
    @Autowired
    private AktuelService service;

    @GetMapping(path = {"/hist", ""})
    public BaseResponse<?> hist() {
        try {
            return BaseResponse.of(service.getHist(), HttpStatus.OK.value());
        } catch (Exception e) {
            return ErrorUtils.badRequest(e);
        }
    }
}
