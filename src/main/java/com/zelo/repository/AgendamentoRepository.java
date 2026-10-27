package com.zelo.repository;

import com.zelo.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByUsuarioIdOrderByDataHorarioAsc(Long usuarioId);
}