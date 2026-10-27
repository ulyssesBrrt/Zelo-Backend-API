package com.zelo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "registro_dose")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private Long medicamentoId;
    private String nomeMedicamento;
    private String quantidade;
    private String horario;
    private LocalDate dataRegistro;
    private String status;
}