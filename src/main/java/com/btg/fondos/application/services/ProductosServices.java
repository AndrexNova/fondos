package com.btg.fondos.application.services;

import org.springframework.stereotype.Service;
import com.btg.fondos.domain.models.Producto;
import com.btg.fondos.domain.port.outbound.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.btg.fondos.domain.exceptions.ProductoNotFoundException;

import java.util.List;

@Service
public class ProductosServices {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public Producto getId(String id) {
        return productoRepository.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));
    }

    public Producto add(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto update(String id, Producto producto) {
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException(id);
        }
        producto.setId(id);
        return productoRepository.save(producto);
    }

    public void delete(String id) {
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException(id);
        }
        productoRepository.deleteById(id);
    }

}
