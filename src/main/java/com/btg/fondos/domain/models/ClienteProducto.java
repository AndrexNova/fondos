package com.btg.fondos.domain.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clienteProduto")
public class ClienteProducto {

    @Id
    private String id ;
    private String idCliente ;
    private String idProducto ;
    private int montoInversion ;
    private boolean estado;

    public ClienteProducto() {
    }

    public ClienteProducto(String id, String idCliente, String idProducto, boolean estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getMontoInversion() {
        return montoInversion;
    }

    public void setMontoInversion(int montoInversion) {
        this.montoInversion = montoInversion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
