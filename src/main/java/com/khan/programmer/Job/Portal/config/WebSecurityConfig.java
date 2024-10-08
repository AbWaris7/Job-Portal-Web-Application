package com.khan.programmer.Job.Portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http){
        return http.build();
    }
}
