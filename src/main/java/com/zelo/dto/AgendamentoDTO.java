package com.zelo.dto;

import com.zelo.entity.TipoAgendamento;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AgendamentoDTO {
    private Long id;
    private Long usuarioId;
    private TipoAgendamento tipo;
    private String nomeClinica;
    private String medico;
    private String especialidade;
    private String laboratorio;
    private String tipoExame;
    private String medicoSolicitante;
    private LocalDateTime dataHorario;
}