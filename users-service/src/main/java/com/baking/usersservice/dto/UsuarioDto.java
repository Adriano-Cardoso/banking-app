package com.baking.usersservice.dto;


import com.baking.usersservice.entities.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UsuarioDto {

    private Integer id;
    private String nomme;
    private String email;

    public UsuarioDto(Usuario entidade) {
        this.id = entidade.getId();
        this.nomme = entidade.getNome();
        this.email = entidade.getEmail();
    }
}
