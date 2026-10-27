package com.zelo.repository;

import com.zelo.entity.ContatoEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoEmergenciaRepository extends JpaRepository<ContatoEmergencia, Long> {

    List<ContatoEmergencia> findByUsuarioId(Long usuarioId);
}
