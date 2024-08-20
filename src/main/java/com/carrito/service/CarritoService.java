/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.service;

import com.carrito.Model.Carrito;
import com.carrito.config.ResourceNotFoundException;
import com.carrito.repository.CarritoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIO
 */
@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> getAllCarritos() {
        return carritoRepository.findAll();
    }

    public Optional<Carrito> getCarritoById(Long id) {
        return carritoRepository.findById(id);
    }

    public Carrito createCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito updateCarrito(Long id, Carrito carritoDetails) {
        return carritoRepository.findById(id)
            .map(carrito -> {
                carrito.setUsuario(carritoDetails.getUsuario());
                return carritoRepository.save(carrito);
            }).orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado con id " + id));
    }

    public void deleteCarrito(Long id) {
        carritoRepository.deleteById(id);
    }
    
}
