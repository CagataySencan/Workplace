package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.cache.Cache;
import com.tdonuk.sepetim.service.DiscountService;
import com.tdonuk.sepetim.util.ErrorUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/discounts")
public class DiscountController {
    @Autowired
    private DiscountService service;

    @GetMapping(path = {"/hist", ""})
    public BaseResponse<?> hist(HttpServletResponse servletResponse) {
        try {
            return BaseResponse.of(Cache.getDiscountHist(), HttpStatus.OK.value());
        } catch (Exception e) {
            return ErrorUtils.badRequest(e, servletResponse);
        }
    }
}
