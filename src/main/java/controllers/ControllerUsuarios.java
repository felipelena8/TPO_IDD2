package controllers;

import dtos.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;
import models.Usuario;
import repositories.UserRepository;

public class ControllerUsuarios {
    private UserRepository repo = new UserRepository();

    private static ControllerUsuarios instancia = null;
    @Getter
    @Setter
    private Usuario session;

    private ControllerUsuarios() {
    }

    public static ControllerUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new ControllerUsuarios();
        }
        return instancia;
    }

    public static int idUsuario() {
        return ControllerUsuarios.getInstancia().getSession().getId();
    }

    public void registrarUsuario(Usuario usuario) {
        if(repo.readPorUsername(usuario.getUsername())==null) {
            repo.save(usuario);
            System.out.println("Se ha registrado un nuevo usuario");

        } else {
            System.out.println("Ya existe un usuario con ese username");
        }
    }

    public void iniciarSesion(UsuarioDTO usuario) {
        Usuario user = repo.readPorUsername(usuario.getUsername());
        if(user!=null && user.getPassword().equals(usuario.getPassword())){
            System.out.println("Se ha iniciado sesion. Bienvenido " + usuario.getUsername());
            setSession(user);
        }
    }
    public void cerrarSesion() {
        session = null;
    }
}
