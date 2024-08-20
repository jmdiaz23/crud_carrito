/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.service;

import com.carrito.Model.LoginRequest;
import com.carrito.Model.Usuario;
import com.carrito.config.JwtUtil;
import com.carrito.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIO
 */
@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil; // Herramienta para generar y validar tokens JWT

    public String login(LoginRequest loginRequest) throws Exception {
        // Busca el usuario por email
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(loginRequest.getEmail());

        // Verifica si el usuario existe
        if (usuarioOptional.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        // Verifica si la contraseña es correcta
        if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            return jwtUtil.generateToken(usuario); // Generar el token JWT
        } else {
            throw new Exception("Contraseña incorrecta");
        }
    }
}
