package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoEmergenciaDTO {

    private String nome;
    private String telefone;
    private String parentesco;
}
