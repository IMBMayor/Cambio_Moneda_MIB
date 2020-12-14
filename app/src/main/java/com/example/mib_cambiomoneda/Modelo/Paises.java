package com.example.mib_cambiomoneda.Modelo;

public class Paises {
    long Id;
    String Nombre;
    String Siglas;
    double Precio;

    public Paises() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }
}
