package com.zelo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "alarme")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alarme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "adiar_minutos")
    private Integer adiarMinutos = 10;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicamento_id", nullable = false)
    @JsonIgnore
    private Medicamento medicamento;

    @OneToMany(mappedBy = "alarme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroMedicacao> registros;
}
