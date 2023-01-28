package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.security.Context;
import com.tdonuk.sepetim.service.BaseService;
import com.tdonuk.sepetim.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController extends BaseController<UserDTO> {
    @Autowired
    private UserService service;

    @Override
    protected BaseService service() {
        return service;
    }

    @GetMapping(path = "/me")
    public ResponseEntity<?> getMe(HttpServletResponse servletResponse) throws Exception {
        return ResponseEntity.ok(BaseResponse.of(Context.loggedUser()));
    }
}
