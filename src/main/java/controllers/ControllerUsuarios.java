package controllers;

import dtos.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuarios {
    private static ControllerUsuarios instancia = null;
    @Getter
    private List<Usuario> usuarios;

    private ControllerUsuarios(){usuarios = new ArrayList<>();}
    @Getter @Setter
    private Usuario session;
    public static ControllerUsuarios getInstancia(){
        if (instancia==null){
            instancia=new ControllerUsuarios();
        }
        return instancia;
    }
    public static int idUsuario(){
        return ControllerUsuarios.getInstancia().getSession().getId();
    }
    public void registrarUsuario(Usuario usuario){
        if(usuarios.stream().filter(user -> user.getUsername().equals(usuario.getUsername()) && user.getPassword().equals(usuario.getPassword())).findFirst().orElse(null)==null){
            usuarios.add(usuario);
            System.out.println("No existe");
        }else{
            System.out.println("Existe");
        }
    }

    public void iniciarSesion(UsuarioDTO usuario){
        Usuario user = usuario.buscarUsuario();
        if(user!=null){
            setSession(user);
        }
    }

    public void eliminarUsuario(UsuarioDTO usuario){
        Usuario user = usuario.buscarUsuario();
        if(user!=null){
            usuarios.remove(user);
        }
    }
    public void cerrarSesion(){
        session = null;
    }
}
