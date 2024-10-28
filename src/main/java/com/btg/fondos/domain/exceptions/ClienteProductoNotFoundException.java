package com.btg.fondos.domain.exceptions;

public class ClienteProductoNotFoundException extends RuntimeException {
    public ClienteProductoNotFoundException(String id) {
        super("No se encontró el producto relacionado ID: " + id);
    }
}