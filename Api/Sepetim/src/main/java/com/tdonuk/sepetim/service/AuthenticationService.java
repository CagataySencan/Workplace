package com.tdonuk.sepetim.service;

import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.sepetim.dao.UserDAO;
import com.tdonuk.sepetim.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.tdonuk.constant.HttpHeaders.ACCESS_TOKEN;
import static com.tdonuk.sepetim.service.constants.UserFields.EMAIL;
import static com.tdonuk.sepetim.service.constants.UserFields.PASSWORD;

@Service
public class AuthenticationService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDTO register(UserDTO dto) throws Exception {
        dto.setPhone(passwordEncoder.encode(dto.getPassword()));
        dto.setCreated(new Date());
        return userDAO.save(dto);
    }

    public Map<String, String> authenticate(Map<String, String> credentials) throws Exception {
        String email = credentials.get(EMAIL);
        String password = credentials.get(PASSWORD);

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        UserDTO user = userDAO.findByEmail(email);

        String jwt = JWTUtils.createDefault(user.getEmail(), Collections.singletonList("USER"));

        Map<String, String> result = new HashMap<>();
        result.put(ACCESS_TOKEN, jwt);

        return result;
    }
}
