package com.tdonuk.sepetim.security;

import com.tdonuk.sepetim.security.filter.AuthFilter;
import com.tdonuk.sepetim.security.handler.AuthFailureHandler;
import com.tdonuk.sepetim.security.handler.AuthSuccessHandler;
import com.tdonuk.sepetim.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthSuccessHandler successHandler;

    private final AuthFailureHandler failureHandler;

    private final AuthFilter authFilter;

    private final AuthenticationProvider authenticationProvider;

    private final UserService userService;


    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/v1/auth/**", "/api/v1/vendors/**", "/api/v1/discount/**", "/error").permitAll()
                        .requestMatchers("/api/v1/users/**").authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

}
