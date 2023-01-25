package com.tdonuk.sepetim.security.filter;

import com.tdonuk.constant.HttpHeaders;
import com.tdonuk.sepetim.constant.ContextParams;
import com.tdonuk.sepetim.security.Context;
import com.tdonuk.sepetim.security.domain.UserDetail;
import com.tdonuk.sepetim.security.domain.UserDetailService;
import com.tdonuk.sepetim.util.JWTUtils;
import com.tdonuk.util.text.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(Objects.isNull(authHeader) || !authHeader.startsWith(HttpHeaders.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String email;
        try {
            email = JWTUtils.getUser(authHeader);
        } catch (Exception e) {
            response.getOutputStream().write(e.getMessage().getBytes(StandardCharsets.UTF_8));
            filterChain.doFilter(request, response);
            return;
        }
        if(StringUtils.isNotBlank(email) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetail user = (UserDetail) userDetailService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            user.getUser().setPassword("[PROTECTED]");

            Context.set(ContextParams.LOGGED_USER,user.getUser());

            SecurityContextHolder.getContext().setAuthentication(token);

            filterChain.doFilter(request, response);
        }
    }
}
