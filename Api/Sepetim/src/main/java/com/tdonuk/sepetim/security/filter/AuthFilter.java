package com.tdonuk.sepetim.security.filter;

import com.tdonuk.constant.HttpHeaders;
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

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(token);

            filterChain.doFilter(request, response);
        }
    }

    /*

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

    */
}
