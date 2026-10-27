package com.zelo.service;

import com.zelo.dto.RegistroDoseDTO;
import com.zelo.entity.RegistroDose;
import com.zelo.repository.RegistroDoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroDoseService {

    @Autowired
    private RegistroDoseRepository repository;

    public RegistroDose registrar(Long usuarioId, RegistroDoseDTO dto) {
        RegistroDose registro = new RegistroDose();
        registro.setUsuarioId(usuarioId);
        registro.setMedicamentoId(dto.getMedicamentoId());
        registro.setNomeMedicamento(dto.getNome());
        registro.setQuantidade(dto.getQuantidade());
        registro.setHorario(dto.getHorario());
        registro.setDataRegistro(dto.getData());
        registro.setStatus(dto.getStatus() != null ? dto.getStatus() : "tomado");

        return repository.save(registro);
    }

    public List<RegistroDose> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioIdOrderByDataRegistroDescHorarioDesc(usuarioId);
    }
}