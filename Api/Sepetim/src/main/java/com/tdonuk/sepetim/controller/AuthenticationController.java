package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.dto.http.Error;
import com.tdonuk.exception.BaseException;
import com.tdonuk.sepetim.service.AuthenticationService;
import com.tdonuk.sepetim.util.ErrorUtils;
import com.tdonuk.util.validation.UserValidator;
import com.tdonuk.util.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;

    @PostMapping(path = {"","/register", "/signup"})
    public BaseResponse<?> register(@RequestBody UserDTO user) {
        try {
            Validator validator = new UserValidator(user).validate();

            UserDTO registered = authService.register(user);
            registered.setPassword("[PROTECTED]");

            return BaseResponse.of(user, HttpStatus.OK.value());
        } catch (Exception e) {
            return ErrorUtils.badRequest(e);
        }
    }

    @PostMapping(path = {"/login", "/authenticate"})
    public BaseResponse<?> authenticate(@RequestBody Map<String, String> credentials) {
        try {
            return BaseResponse.of(authService.authenticate(credentials), HttpStatus.OK.value());
        } catch (Exception e) {
            return ErrorUtils.forbidden(e);
        }
    }
}
