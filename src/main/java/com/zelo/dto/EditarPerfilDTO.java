package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditarPerfilDTO {

    private String nome;
    private LocalDate dataNascimento;
    private String telefone;
    private String fotoUrl;
}
