package dtos;

import controllers.ControllerUsuarios;
import lombok.Data;
import models.Usuario;

@Data
public class UsuarioDTO {
    private String username;
    private String password;

    public UsuarioDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario buscarUsuario(){
        //TODO usar bd
        return ControllerUsuarios.getInstancia().getUsuarios().stream().filter(usuario->usuario.getUsername().equals(username) && usuario.getPassword().equals(password)).findFirst().orElse(null);
    }

}
