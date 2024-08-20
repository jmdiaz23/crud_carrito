/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.service;

import com.carrito.Model.Factura;
import com.carrito.config.ResourceNotFoundException;
import com.carrito.repository.FacturaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FABIO
 */
@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }

    public Factura createFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura updateFactura(Long id, Factura facturaDetails) {
        return facturaRepository.findById(id)
            .map(factura -> {
                factura.setUsuario(facturaDetails.getUsuario());
                factura.setFecha(facturaDetails.getFecha());
                return facturaRepository.save(factura);
            }).orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id " + id));
    }

    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }
}
