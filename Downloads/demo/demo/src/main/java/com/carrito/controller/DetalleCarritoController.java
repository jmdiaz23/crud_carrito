/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.controller;

import com.carrito.Model.DetalleCarrito;
import com.carrito.config.ResourceNotFoundException;
import com.carrito.service.DetalleCarritoService;
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
@RequestMapping("/detallesCarrito")
public class DetalleCarritoController {
    @Autowired
    private DetalleCarritoService detalleCarritoService;

    @GetMapping
    public List<DetalleCarrito> getAllDetallesCarrito() {
        return detalleCarritoService.getAllDetallesCarrito();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCarrito> getDetalleCarritoById(@PathVariable Long id) {
        Optional<DetalleCarrito> detalleCarrito = detalleCarritoService.getDetalleCarritoById(id);
        return detalleCarrito.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleCarrito createDetalleCarrito(@RequestBody DetalleCarrito detalleCarrito) {
        return detalleCarritoService.createDetalleCarrito(detalleCarrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCarrito> updateDetalleCarrito(@PathVariable Long id, @RequestBody DetalleCarrito detalleCarritoDetails) {
        try {
            DetalleCarrito updatedDetalleCarrito = detalleCarritoService.updateDetalleCarrito(id, detalleCarritoDetails);
            return ResponseEntity.ok(updatedDetalleCarrito);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleCarrito(@PathVariable Long id) {
        detalleCarritoService.deleteDetalleCarrito(id);
        return ResponseEntity.noContent().build();
    }
}
