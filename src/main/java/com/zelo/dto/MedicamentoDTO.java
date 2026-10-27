package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoDTO {

    private String nome;
    private String dosagem;
    private String formato;
    private String viaAdministracao;
    private Integer quantidadeEstoque;
}
