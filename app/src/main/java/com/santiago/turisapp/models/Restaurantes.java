package com.santiago.turisapp.models;

import java.io.Serializable;

public class Restaurantes implements Serializable {
    private String nombre,descripsion,ubicacon;
    private int imagen;

    public Restaurantes() {
    }

    public Restaurantes(String nombre, String descripsion, String ubicacon, int imagen) {
        this.nombre = nombre;
        this.descripsion = descripsion;
        this.ubicacon = ubicacon;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }

    public String getUbicacon() {
        return ubicacon;
    }

    public void setUbicacon(String ubicacon) {
        this.ubicacon = ubicacon;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
