package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformacoesSaudeDTO {

    private String tipoSanguineo;
    private String alergias;
    private String condicaoSaude;
    private String observacoesImportantes;
}
