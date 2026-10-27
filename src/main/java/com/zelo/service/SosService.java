package com.zelo.service;

import com.zelo.dto.ContatoSosDTO;
import com.zelo.entity.ContatoEmergencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SosService {

    @Autowired
    private ContatoEmergenciaService contatoEmergenciaService;

    public List<ContatoSosDTO> obterContatosParaAcionamento(Long usuarioId) {
        List<ContatoEmergencia> contatos = contatoEmergenciaService.listarPorUsuario(usuarioId);
        List<ContatoSosDTO> resultado = new ArrayList<>();

        for (int i = 0; i < contatos.size(); i++) {
            ContatoEmergencia c = contatos.get(i);
            resultado.add(new ContatoSosDTO(c.getNome(), c.getTelefone(), c.getParentesco(), i == 0));
        }

        resultado.add(new ContatoSosDTO("SAMU", "192", "Emergência médica", false));

        return resultado;
    }
}
