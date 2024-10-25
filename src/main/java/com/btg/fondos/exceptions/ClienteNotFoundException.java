package com.btg.fondos.exceptions;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String id) {
        super("No se encontró el client con ID: " + id);
    }
}