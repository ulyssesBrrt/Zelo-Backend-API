package com.zelo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAgendamento tipo;

    // Campos para Consulta
    private String nomeClinica;
    private String medico;
    private String especialidade;

    // Campos para Exame
    private String laboratorio;
    private String tipoExame;
    private String medicoSolicitante;

    @Column(nullable = false)
    private LocalDateTime dataHorario;
}