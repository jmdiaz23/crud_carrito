/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.service;

import com.carrito.Model.DetalleFactura;
import com.carrito.config.ResourceNotFoundException;
import com.carrito.repository.DetalleFacturaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIO
 */
@Service
public class DetalleFacturaService {
    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    public List<DetalleFactura> getAllDetallesFactura() {
        return detalleFacturaRepository.findAll();
    }

    public Optional<DetalleFactura> getDetalleFacturaById(Long id) {
        return detalleFacturaRepository.findById(id);
    }

    public DetalleFactura createDetalleFactura(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    public DetalleFactura updateDetalleFactura(Long id, DetalleFactura detalleFacturaDetails) {
        return detalleFacturaRepository.findById(id)
            .map(detalleFactura -> {
                detalleFactura.setFactura(detalleFacturaDetails.getFactura());
                detalleFactura.setProducto(detalleFacturaDetails.getProducto());
                detalleFactura.setCantidad(detalleFacturaDetails.getCantidad());
                detalleFactura.setPrecio(detalleFacturaDetails.getPrecio());
                return detalleFacturaRepository.save(detalleFactura);
            }).orElseThrow(() -> new ResourceNotFoundException("DetalleFactura no encontrado con id " + id));
    }

    public void deleteDetalleFactura(Long id) {
        detalleFacturaRepository.deleteById(id);
    }
}
