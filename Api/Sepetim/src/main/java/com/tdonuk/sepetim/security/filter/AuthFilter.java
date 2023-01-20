package com.tdonuk.sepetim.security.filter;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdonuk.constant.Time;
import com.tdonuk.exception.BadRequestException;
import com.tdonuk.sepetim.constant.ContextParams;
import com.tdonuk.sepetim.security.Context;
import com.tdonuk.sepetim.security.domain.UserDetail;
import com.tdonuk.sepetim.security.handler.AuthFailureHandler;
import com.tdonuk.sepetim.security.handler.AuthSuccessHandler;
import com.tdonuk.sepetim.service.UserService;
import com.tdonuk.sepetim.util.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AuthFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private AuthSuccessHandler successHandler;

    @Autowired
    private AuthFailureHandler failureHandler;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        setUsernameParameter("email");
        setAuthenticationSuccessHandler(successHandler);
        setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.info(String.format("authentication attempt from [%s]", request.getRemoteAddr()));

        if("POST".equalsIgnoreCase(request.getMethod())) {
            String username, password;

            String body = null;
            JsonNode json = null;
            try {
                body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                json = new ObjectMapper().readTree(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(Objects.isNull(json)) throw new RuntimeException("geçersiz istek: body null");

            username = json.get("email").asText();
            password = json.get("password").asText();

            logger.info(String.format("authenticating [%s] as [%s]...", request.getRemoteAddr(), username));

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

            return this.authenticationManager.authenticate(token);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        logger.info(String.format("Authentication success: [%s]", request.getRemoteAddr()));

        UserDetail userDetails = (UserDetail) authResult.getPrincipal();

        if(Objects.isNull(userDetails)) {
            response.setStatus(400);
            new ObjectMapper().writeValue(response.getOutputStream(), "An unknown error is happened while logging in.. please re-login in order to continue");
            return;
        };

        logger.info(String.format("creating tokens for authenticated user[%s]...", userDetails.getUsername()));

        String accessToken = JWTUtils.createDefault(userDetails.getUsername(), List.of("USER"));
        String refreshToken = JWTUtils.create(userDetails.getUsername(), Time.YEAR, Algorithm.HMAC256("example".getBytes()), List.of());

        logger.info(String.format("access and refresh tokens created for authenticated user[%s], preparing response..", userDetails.getUsername()));

        response.setHeader("access_token", accessToken);
        response.setHeader("refresh_token", refreshToken);
        response.setHeader("Content-Type", "application/json; charset=UTF-8");

        Context.setAttr(ContextParams.LOGGED_USER, userDetails.getUser());
        Context.setAttr(ContextParams.LOGGED_USERNAME, userDetails.getUsername());

        userDetails.getUser().setPassword("[PROTECTED]");

        new ObjectMapper().writeValue(response.getOutputStream(), userDetails.getUser());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
