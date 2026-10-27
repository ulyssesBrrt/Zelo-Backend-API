package com.zelo.repository;

import com.zelo.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    List<Medicamento> findByUsuarioId(Long UsuarioId);

    List<Medicamento> findByUsuarioIdAndQuantidadeEstoqueLessThanEqual(Long usuarioId, Integer limite);
}
