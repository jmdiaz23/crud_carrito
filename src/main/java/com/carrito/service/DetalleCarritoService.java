/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.service;

import com.carrito.Model.DetalleCarrito;
import com.carrito.config.ResourceNotFoundException;
import com.carrito.repository.DetalleCarritoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIO
 */
@Service
public class DetalleCarritoService {
    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    public List<DetalleCarrito> getAllDetallesCarrito() {
        return detalleCarritoRepository.findAll();
    }

    public Optional<DetalleCarrito> getDetalleCarritoById(Long id) {
        return detalleCarritoRepository.findById(id);
    }

    public DetalleCarrito createDetalleCarrito(DetalleCarrito detalleCarrito) {
        return detalleCarritoRepository.save(detalleCarrito);
    }

    public DetalleCarrito updateDetalleCarrito(Long id, DetalleCarrito detalleCarritoDetails) {
        return detalleCarritoRepository.findById(id)
            .map(detalleCarrito -> {
                detalleCarrito.setCarrito(detalleCarritoDetails.getCarrito());
                detalleCarrito.setProducto(detalleCarritoDetails.getProducto());
                detalleCarrito.setCantidad(detalleCarritoDetails.getCantidad());
                return detalleCarritoRepository.save(detalleCarrito);
            }).orElseThrow(() -> new ResourceNotFoundException("DetalleCarrito no encontrado con id " + id));
    }

    public void deleteDetalleCarrito(Long id) {
        detalleCarritoRepository.deleteById(id);
    }
}
