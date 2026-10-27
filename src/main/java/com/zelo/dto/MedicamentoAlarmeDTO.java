package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoAlarmeDTO {

    private String nome;
    private String dosagem;
    private String formato;
    private String viaAdministracao;
    private LocalTime horario;
    private Integer quantidadeEstoque;
}
