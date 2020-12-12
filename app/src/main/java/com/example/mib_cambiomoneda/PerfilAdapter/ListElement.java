package com.example.mib_cambiomoneda.PerfilAdapter;

public class ListElement {

    public String color;
    public String NombreUsuario;
    public String EmailUsuario;
    public String PaisUsuario;


    public ListElement(String color, String NombreUsuario, String EmailUsuario, String PaisUsuario) {
        this.color = color;
        this.NombreUsuario = NombreUsuario;
        this.EmailUsuario = EmailUsuario;
        this.PaisUsuario = PaisUsuario;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getEmailUsuario() {
        return EmailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        EmailUsuario = emailUsuario;
    }

    public String getPaisUsuario() {
        return PaisUsuario;
    }

    public void setPaisUsuario(String paisUsuario) {
        PaisUsuario = paisUsuario;
    }
}
