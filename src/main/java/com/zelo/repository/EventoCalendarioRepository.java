package com.zelo.repository;

import com.zelo.entity.EventoCalendario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoCalendarioRepository extends JpaRepository<EventoCalendario, Long> {

    List<EventoCalendario> findByUsuarioId(Long usuarioId);

    List<EventoCalendario> findByUsuarioIdAndDataHoraBetween(Long usuarioId, LocalDateTime inicio, LocalDateTime fim);

    boolean existsByUsuarioIdAndTipoAndTitulo(Long usuarioId, String tipo, String titulo);
}
