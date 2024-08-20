/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.controller;
import com.carrito.config.ResourceNotFoundException;
import com.carrito.Model.DetalleFactura;
import com.carrito.service.DetalleFacturaService;
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
@RequestMapping("/detallesFactura")
public class DetalleFacturaController {
    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @GetMapping
    public List<DetalleFactura> getAllDetallesFactura() {
        return (List<DetalleFactura>) detalleFacturaService.getAllDetallesFactura();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> getDetalleFacturaById(@PathVariable Long id) {
        Optional<DetalleFactura> detalleFactura = (Optional<DetalleFactura>) detalleFacturaService.getDetalleFacturaById(id);
        return detalleFactura.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleFactura createDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        return (DetalleFactura) detalleFacturaService.createDetalleFactura(detalleFactura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> updateDetalleFactura(@PathVariable Long id, @RequestBody DetalleFactura detalleFacturaDetails) {
        try {
            DetalleFactura updatedDetalleFactura = (DetalleFactura) detalleFacturaService.updateDetalleFactura(id, detalleFacturaDetails);
            return ResponseEntity.ok(updatedDetalleFactura);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleFactura(@PathVariable Long id) {
        detalleFacturaService.deleteDetalleFactura(id);
        return ResponseEntity.noContent().build();
    }
}
