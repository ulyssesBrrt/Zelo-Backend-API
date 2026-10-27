package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoResumoDiaDTO {

    private LocalDate data;
    private int tomados;
    private int pendentes;
    private List<HistoricoItemDTO> itens;
}
