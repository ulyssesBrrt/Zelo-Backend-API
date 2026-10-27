package com.zelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmeDTO {

    private LocalTime hora;
    private Integer adiarMinutos;
    private Long medicamentoId;
}
