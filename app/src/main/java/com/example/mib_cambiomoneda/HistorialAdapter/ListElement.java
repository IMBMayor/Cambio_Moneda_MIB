package com.example.mib_cambiomoneda.HistorialAdapter;

public class ListElement {

    public String color;
    public String paisOrigen;
    public String montoOrigen;
    public String paisDestino;
    public String montoDestino;

    public ListElement(String color, String paisOrigen, String montoOrigen, String paisDestino, String montoDestino) {
        this.color = color;
        this.paisOrigen = paisOrigen;
        this.montoOrigen = montoOrigen;
        this.paisDestino = paisDestino;
        this.montoDestino = montoDestino;
    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getMontoOrigen() {
        return montoOrigen;
    }

    public void setMontoOrigen(String montoOrigen) {
        this.montoOrigen = montoOrigen;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public String getMontoDestino() {
        return montoDestino;
    }

    public void setMontoDestino(String montoDestino) {
        this.montoDestino = montoDestino;
    }
}
