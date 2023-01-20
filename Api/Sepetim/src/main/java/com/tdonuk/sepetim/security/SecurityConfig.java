package com.tdonuk.sepetim.security;

import com.tdonuk.sepetim.security.domain.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailService userDetailService;


    @Bean
    public SecurityFilterChain authenticated(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .securityMatcher("/api/me", "/api/me/**")
                .authorizeHttpRequests((req) ->
                        req.anyRequest().authenticated()
                )
                .userDetailsService(userDetailService)
                .httpBasic();

        return http.build();
    }

    @Bean
    public SecurityFilterChain unauthenticated(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .securityMatcher("/api/actuals", "/api/actuals/**")
                .authorizeHttpRequests((req) ->
                        req.anyRequest().permitAll()
                )
                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailService;
    }
}
