/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.repository;

import com.carrito.Model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FABIO
 */
@Repository
public interface  UsuarioRepository extends JpaRepository<Usuario, Long>{
     //Usuario findByEmail(String email);
    Optional<Usuario> findByEmail(String email);

}
