package com.btg.fondos.domain.exceptions;

public class TransaccionesNotFoundException extends RuntimeException {
    public TransaccionesNotFoundException(String id) {
        super("No se encontró la transacción ID: " + id);
    }
}