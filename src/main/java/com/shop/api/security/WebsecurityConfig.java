package com.shop.api.security;

import com.shop.service.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.stereotype.Component;

@Configuration
public class WebsecurityConfig {

    private JWTRequestFilter jwtRequestFilter;

    public WebsecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable().cors();
        httpSecurity.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/zadania/mostpopular","/wynik","/zadania/*/wykonane/*","/product","/auth/register","/kompilator2","/auth/login","/users","/admin/form","/admin/users","/zadania/all","/kompilator","/zadania/komentarze/*/*","/auth/me","/zadania/komentarze/*").permitAll()
                .anyRequest().authenticated();
        return httpSecurity.build();
    }
}
