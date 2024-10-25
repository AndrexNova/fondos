package com.btg.fondos.exceptions;

public class TransaccionesNotFoundException extends RuntimeException {
    public TransaccionesNotFoundException(String id) {
        super("No se encontró la transacción ID: " + id);
    }
}