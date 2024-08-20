/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.controller;

import com.carrito.Model.LoginRequest;
import com.carrito.Model.Usuario;
import com.carrito.config.AppConfig;
import com.carrito.service.AuthService;
import com.carrito.service.UsuarioService;
import static org.hibernate.internal.CoreLogging.logger;
import static org.hibernate.internal.HEMLogging.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FABIO
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private UsuarioService usuarioService; 

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
       
        
        try {
            String token = authService.login(loginRequest);
            return ResponseEntity.ok().body("Token: " + token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        // se encripta la contraseña antes de guardar el usuario
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioService.createUsuario(usuario); // se llama al método que guarda el usuario
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

}
