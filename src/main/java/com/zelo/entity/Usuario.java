package com.zelo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "tipo_usuario", nullable = false)
    private String tipoUsuario;

    @Column
    private String telefone;

    @Column(name = "foto_url")
    private String fotoUrl;

    @Column(name = "tipo_sanguineo")
    private String tipoSanguineo;

    @Column
    private String alergias;

    @Column(name = "condicao_saude")
    private String condicaoSaude;

    @Column(name = "observacoes_importantes", columnDefinition = "TEXT")
    private String observacoesImportantes;

    // ----- Campos de Contato de Emergência (SOS) -----
    @Column(name = "contato_nome")
    private String contatoNome;

    @Column(name = "contato_telefone")
    private String contatoTelefone;

    @Column(name = "contato_parentesco")
    private String contatoParentesco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuidador_id")
    private Usuario cuidador;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicamento> medicamentos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContatoEmergencia> contatosEmergencia;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoCalendario> eventosCalendario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacao> notificacoes;
}