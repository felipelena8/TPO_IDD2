package models;

public class Comentario {
    private Usuario usuario;
    private String mensaje;

    @Override
    public String toString() {
        return "autor: " + usuario.getUsername() + " mensaje: " + mensaje;
    }
}
