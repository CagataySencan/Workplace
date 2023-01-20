package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.service.BaseService;
import com.tdonuk.sepetim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<UserDTO> {
    @Autowired
    private UserService service;

    @Override
    protected BaseService service() {
        return service;
    }

    @GetMapping(path = "/me")
    public BaseResponse<?> getMe() {
        return null;
    }
}
