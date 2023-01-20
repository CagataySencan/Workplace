package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.service.ActualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actuals")
public class ActualController {
    @Autowired
    private ActualService service;

    @GetMapping(path = {"", "/"})
    public BaseResponse<?> current() {
        try {
            return BaseResponse.of(service.getCurrent(), HttpStatus.OK.value());
        } catch (Exception e) {
            return BaseResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @GetMapping(path = {"/hist"})
    public BaseResponse<?> hist() {
        try {
            return BaseResponse.of(service.getHist(), HttpStatus.OK.value());
        } catch (Exception e) {
            return BaseResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }
}
