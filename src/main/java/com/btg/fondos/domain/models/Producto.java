package com.btg.fondos.domain.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "producto")
public class Producto {

    @Id
    private String id ;
    private String nombre ;
    private int montoMinimo ;
    private String categoria;

    public Producto() {
    }

    public Producto(String id, String nombre, int montoMinimo, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.montoMinimo = montoMinimo;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(int montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
