package com.btg.fondos.domain.exceptions;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(String id) {
        super("No se encontró el producto con ID: " + id);
    }
}