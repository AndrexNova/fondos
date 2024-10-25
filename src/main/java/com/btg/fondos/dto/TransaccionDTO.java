package com.btg.fondos.dto;

import com.btg.fondos.models.Cliente;
import com.btg.fondos.models.Producto;
import com.btg.fondos.models.Transaccion;

public class TransaccionDTO {

    private String id ;
    private boolean estado ;
    private String nombreCliente;
    private String nombreFondo;

    public TransaccionDTO() {
    }

    public TransaccionDTO(Transaccion transaccion, Cliente cliente, Producto producto){
        this.id = transaccion.getId();
        this.estado= transaccion.getClienteProducto().isEstado();
        this.nombreCliente = cliente.getNombre();
        this.nombreFondo = producto.getNombre();


    }

    public TransaccionDTO(String id, boolean estado, String nombreCliente, String nombreFondo) {
        this.id = id;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
        this.nombreFondo = nombreFondo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreFondo() {
        return nombreFondo;
    }

    public void setNombreFondo(String nombreFondo) {
        this.nombreFondo = nombreFondo;
    }
}
