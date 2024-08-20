/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.service;

import com.carrito.Model.Producto;
import com.carrito.config.ResourceNotFoundException;
import com.carrito.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIO
 */
@Service
public class ProductoService {
     @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto productoDetails) {
        return productoRepository.findById(id)
            .map(producto -> {
                producto.setNombre(productoDetails.getNombre());
                producto.setDescripcion(productoDetails.getDescripcion());
                producto.setPrecio(productoDetails.getPrecio());
                return productoRepository.save(producto);
            }).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id " + id));
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
    
}
