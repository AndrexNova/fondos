package com.btg.fondos.exceptions;

public class ClienteProductoNotFoundException extends RuntimeException {
    public ClienteProductoNotFoundException(String id) {
        super("No se encontró el producto relacionado ID: " + id);
    }
}