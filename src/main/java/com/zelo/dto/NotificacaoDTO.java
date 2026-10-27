package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private String tipo;
    private Boolean lida;
}
