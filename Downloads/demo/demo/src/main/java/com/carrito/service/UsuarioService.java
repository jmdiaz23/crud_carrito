/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.service;

import com.carrito.Model.Usuario;
import com.carrito.config.ResourceNotFoundException;
import com.carrito.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIO
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setNombre(usuarioDetails.getNombre());
                usuario.setEmail(usuarioDetails.getEmail());
                usuario.setPassword(usuarioDetails.getPassword());
                return usuarioRepository.save(usuario);
            }).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id " + id));
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
