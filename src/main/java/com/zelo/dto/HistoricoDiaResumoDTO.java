package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoDiaResumoDTO {

    private LocalDate data;
    private int tomados;
    private int pendaentes;
}
