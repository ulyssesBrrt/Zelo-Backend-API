package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoItemDTO {

    private String medicamento;
    private String dosagem;
    private LocalTime horario;
    private String status;
}
