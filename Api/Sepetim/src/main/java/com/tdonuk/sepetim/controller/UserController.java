package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.service.BaseService;
import com.tdonuk.sepetim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends BaseController<UserDTO> {
    @Autowired
    private UserService service;

    @Override
    protected BaseService service() {
        return service;
    }

    @GetMapping(path = "/me")
    public BaseResponse<?> getMe(Principal principal) {
        try {
            return BaseResponse.of(service.findByEmail(principal.getName()), HttpStatus.OK.value());
        } catch (Exception e) {
            return BaseResponse.of(e.getMessage(), HttpStatus.FORBIDDEN.value());
        }
    }
}
