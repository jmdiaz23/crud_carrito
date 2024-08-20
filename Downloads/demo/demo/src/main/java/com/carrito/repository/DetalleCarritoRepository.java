/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.repository;

import com.carrito.Model.DetalleCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FABIO
 */
@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {
    // Puedes agregar métodos personalizados de consulta si es necesario
}
