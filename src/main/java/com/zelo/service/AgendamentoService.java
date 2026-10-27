package com.zelo.service;

import com.zelo.dto.AgendamentoDTO;
import com.zelo.entity.Agendamento;
import com.zelo.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public List<Agendamento> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioIdOrderByDataHorarioAsc(usuarioId);
    }

    public Agendamento criarAgendamento(AgendamentoDTO dto) {
        Agendamento agendamento = Agendamento.builder()
                .usuarioId(dto.getUsuarioId())
                .tipo(dto.getTipo())
                .nomeClinica(dto.getNomeClinica())
                .medico(dto.getMedico())
                .especialidade(dto.getEspecialidade())
                .laboratorio(dto.getLaboratorio())
                .tipoExame(dto.getTipoExame())
                .medicoSolicitante(dto.getMedicoSolicitante())
                .dataHorario(dto.getDataHorario())
                .build();

        return repository.save(agendamento);
    }
}