package com.example.mib_cambiomoneda.Modelo;

public class Paises {

    String Nombre;
    String Siglas;
    float Precio;

    public Paises() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getSiglas() {
        return Siglas;
    }

    public void setSiglas(String siglas) {
        Siglas = siglas;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }
}
