package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroUsuarioDTO {

    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String senha;
    private String tipoUsuario;
}
