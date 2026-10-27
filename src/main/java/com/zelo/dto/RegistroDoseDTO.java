package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDoseDTO {
    private Long medicamentoId;
    private String nome;
    private String quantidade;
    private String horario;
    private LocalDate data;
    private String status;
}