package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.security.Context;
import com.tdonuk.sepetim.service.BaseService;
import com.tdonuk.sepetim.service.UserService;
import com.tdonuk.sepetim.util.ErrorUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public BaseResponse<?> getMe(HttpServletResponse servletResponse) {
        try {
            return BaseResponse.of(Context.loggedUser(), HttpStatus.OK.value());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ErrorUtils.unauthorized(e, servletResponse);
        }
    }
}
