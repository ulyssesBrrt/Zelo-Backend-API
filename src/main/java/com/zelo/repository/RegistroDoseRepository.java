package com.zelo.repository;

import com.zelo.entity.RegistroDose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroDoseRepository extends JpaRepository<RegistroDose, Long> {
    // Busca o histórico do utilizador, ordenando do mais recente para o mais antigo
    List<RegistroDose> findByUsuarioIdOrderByDataRegistroDescHorarioDesc(Long usuarioId);
}