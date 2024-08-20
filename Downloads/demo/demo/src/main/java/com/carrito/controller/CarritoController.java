/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.controller;

import com.carrito.Model.Carrito;
import com.carrito.Model.ItemCarrito;
import com.carrito.config.ResourceNotFoundException;
import com.carrito.service.CarritoService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FABIO
 */
@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Carrito> getAllCarritos() {
        return carritoService.getAllCarritos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable Long id) {
        Optional<Carrito> carrito = carritoService.getCarritoById(id);
        return carrito.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carrito createCarrito(@RequestBody Carrito carrito) {
        return carritoService.createCarrito(carrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> updateCarrito(@PathVariable Long id, @RequestBody Carrito carritoDetails) {
        try {
            Carrito updatedCarrito = carritoService.updateCarrito(id, carritoDetails);
            return ResponseEntity.ok(updatedCarrito);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrito(@PathVariable Long id) {
        carritoService.deleteCarrito(id);
        return ResponseEntity.noContent().build();
    }
}
