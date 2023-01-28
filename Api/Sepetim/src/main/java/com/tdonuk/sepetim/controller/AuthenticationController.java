package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.service.AuthenticationService;
import com.tdonuk.util.validation.UserValidator;
import com.tdonuk.util.validation.Validator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> register(@RequestBody UserDTO user, HttpServletResponse servletResponse) throws Exception {
        Validator validator = new UserValidator(user).validate();

        UserDTO registered = authService.register(user);
        registered.setPassword("[PROTECTED]");

        return ResponseEntity.ok(BaseResponse.of(user));
    }

    @PostMapping(path = {"/login", "/authenticate"})
    public Object authenticate(@RequestBody Map<String, String> credentials) throws Exception {
        return ResponseEntity.ok(authService.authenticate(credentials));
    }
}
