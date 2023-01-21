package com.tdonuk.sepetim.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdonuk.sepetim.constant.ContextParams;
import com.tdonuk.sepetim.security.Context;
import com.tdonuk.sepetim.util.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {
    private final List<String> secureList = List.of(
            "/api/user",
            "/login",
            "/test",
            "/api/token/refresh"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(secureList.contains(request.getServletPath())) {
            filterChain.doFilter(request, response);
        } else {
            String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                try {
                    JWTUtils.validate(tokenHeader);

                    Context.setAttr(ContextParams.LOGGED_USERNAME, JWTUtils.getUser(tokenHeader));

                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(JWTUtils.getUser(tokenHeader),tokenHeader, JWTUtils.getAuthorities(tokenHeader));

                    SecurityContextHolder.getContext().setAuthentication(token);

                    filterChain.doFilter(request, response);
                } catch(Exception e) {
                    log.error(e.getMessage());
                    response.setHeader("Content-Type", "application/json; charset=UTF-8");
                    response.setStatus(401);
                    new ObjectMapper().writeValue(response.getOutputStream(), e.getMessage());
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
