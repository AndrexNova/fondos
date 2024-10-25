package com.btg.fondos.exceptions;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(String id) {
        super("No se encontró el producto con ID: " + id);
    }
}