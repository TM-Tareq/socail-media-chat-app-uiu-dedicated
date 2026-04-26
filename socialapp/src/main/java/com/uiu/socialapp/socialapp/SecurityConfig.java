//package com.uiu.socialapp.socialapp;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable) // নতুন ল্যামডা স্টাইল
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()
//                );
//
//        return http.build();
//    }
//}
