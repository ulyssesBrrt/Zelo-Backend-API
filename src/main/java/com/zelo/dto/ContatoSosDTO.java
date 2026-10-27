package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoSosDTO {

    private String nome;
    private String telefone;
    private String parentesco;
    private boolean principal;
}
