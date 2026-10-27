package com.zelo.repository;

import com.zelo.entity.RegistroMedicacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistroMedicacaoRepository extends JpaRepository<RegistroMedicacao, Long> {

    List<RegistroMedicacao> findByAlarmeId(Long alarmeId);

    List<RegistroMedicacao> findByAlarmeMedicamentoUsuarioIdOrderByDataHoraAgendadaDesc(Long usuarioId);

    List<RegistroMedicacao> findByAlarmeMedicamentoUsuarioIdAndDataHoraAgendadaBetween(Long usuarioId, LocalDateTime inicio, LocalDateTime fim);
}
