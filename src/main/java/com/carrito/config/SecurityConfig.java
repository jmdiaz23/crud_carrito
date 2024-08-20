/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author FABIO
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final JwtRequestFilter jwtRequestFilter;
//
//    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
//        this.jwtRequestFilter = jwtRequestFilter;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()
                //.requestMatchers("/**").permitAll()   
                //.requestMatchers("/auth/login", "/auth/register").permitAll() // Rutas públicas
               // .anyRequest().authenticated() // Otras rutas requieren autenticación
            );
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No mantener sesiones
//            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
//    @Bean
//    public JwtRequestFilter jwtRequestFilter() {
//        return new JwtRequestFilter();
//    }
}
