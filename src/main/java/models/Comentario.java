package models;

import java.util.ArrayList;

public class Comentario {
    private Usuario usuario;
    private String mensaje;

    public Comentario(Usuario usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "autor: " + usuario.getUsername() + " mensaje: " + mensaje;
    }
}
