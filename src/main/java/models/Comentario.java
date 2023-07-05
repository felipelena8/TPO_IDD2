package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Comentario {
    @OneToOne(cascade = CascadeType.ALL)
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
