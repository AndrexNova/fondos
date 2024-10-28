package com.btg.fondos.domain.port.response;

import com.btg.fondos.domain.models.Cliente;
import com.btg.fondos.domain.models.Producto;

public class ClienteProductoResponse {

    private String id ;
    private String idCliente;
    private String nombreCliente ;
    private String idProducto ;
    private String fondoCliente;
    private boolean estado;

    private int montoInversion;
    private int montoMinimo;


    public ClienteProductoResponse(Cliente cliente, Producto producto ) {
        this.id = "0";
        this.idCliente =  cliente.getId();
        this.nombreCliente = cliente.getNombre();
        this.idProducto = producto.getId();
        this.fondoCliente = producto.getNombre();
        this.estado = false;
    }

    public ClienteProductoResponse(String id, String idCliente, String nombreCliente, String idProducto, String fondoCliente, boolean estado, int montoInversion, int montoMinimo) {
        this.id = id;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.idProducto = idProducto;
        this.fondoCliente = fondoCliente;
        this.estado = estado;
        this.montoInversion = montoInversion;
        this.montoMinimo = montoMinimo;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getFondoCliente() {
        return fondoCliente;
    }

    public void setFondoCliente(String fondoCliente) {
        this.fondoCliente = fondoCliente;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getMontoInversion() {
        return montoInversion;
    }

    public void setMontoInversion(int montoInversion) {
        this.montoInversion = montoInversion;
    }

    public int getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(int montoMinimo) {
        this.montoMinimo = montoMinimo;
    }
}
