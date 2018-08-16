package com.santiago.turisapp.models;

import java.io.Serializable;

public class Sitios implements Serializable{
    private String nombre;
    private String descripsion;
    private String ubicacion;
    private int imagen;

    public Sitios() {
    }

    public Sitios(String nombre, String descripsion, String ubicacion, int imagen) {
        this.nombre = nombre;
        this.descripsion = descripsion;
        this.ubicacion = ubicacion;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
