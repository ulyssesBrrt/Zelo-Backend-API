package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoCalendarioDTO {

    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private String tipo;
}
