package com.btg.fondos.domain.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transacciones")
public class Transaccion {

    @Id
    private String id ;
    private ClienteProducto clienteProducto ;

    public Transaccion() {
    }

    public Transaccion(String id, ClienteProducto clienteProducto) {
        this.id = id;
        this.clienteProducto = clienteProducto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClienteProducto getClienteProducto() {
        return clienteProducto;
    }

    public void setClienteProducto(ClienteProducto clienteProducto) {
        this.clienteProducto = clienteProducto;
    }
}
