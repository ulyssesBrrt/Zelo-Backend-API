package com.zelo.repository;

import com.zelo.entity.Alarme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmeRepository extends JpaRepository<Alarme, Long> {

    List<Alarme> findByMedicamentoId(Long medicamentoId);

    List<Alarme> findByMedicamentoUsuarioIdAndAtivoTrue(Long usuarioId);
}
