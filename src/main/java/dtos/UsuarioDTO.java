package dtos;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String username;
    private String password;

    public UsuarioDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
