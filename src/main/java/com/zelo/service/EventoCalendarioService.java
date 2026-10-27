package com.zelo.service;

import com.zelo.dto.EventoCalendarioDTO;
import com.zelo.entity.EventoCalendario;
import com.zelo.entity.Medicamento;
import com.zelo.entity.Usuario;
import com.zelo.repository.EventoCalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoCalendarioService {

    @Autowired
    private EventoCalendarioRepository eventoCalendarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NotificacaoService notificacaoService;

    public EventoCalendario cadastrar(Long usuarioId, EventoCalendarioDTO dto) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        EventoCalendario eventoCalendario = new EventoCalendario();
        eventoCalendario.setTitulo(dto.getTitulo());
        eventoCalendario.setDescricao(dto.getDescricao());
        eventoCalendario.setDataHora(dto.getDataHora());
        eventoCalendario.setTipo(dto.getTipo());
        eventoCalendario.setUsuario(usuario);

        EventoCalendario salvo = eventoCalendarioRepository.save(eventoCalendario);
        notificacaoService.notificarEvento(usuario, dto.getTitulo(), dto.getTipo(), dto.getDataHora());
        return salvo;
    }

    public List<EventoCalendario> listarPorUsuario(Long usuarioId) {
        return eventoCalendarioRepository.findByUsuarioId(usuarioId);
    }

    public List<EventoCalendario> listarPorPeriodo(Long usuarioId, LocalDateTime inicio, LocalDateTime fim) {
        return eventoCalendarioRepository.findByUsuarioIdAndDataHoraBetween(usuarioId, inicio, fim);
    }

    public void deletar(Long id) {
        eventoCalendarioRepository.deleteById(id);
    }

    public void criarAlertaEstoqueBaixo(Medicamento medicamento) {
        String titulo = "Estoque Baixo: " + medicamento.getNome();
        boolean jaExiste = eventoCalendarioRepository.existsByUsuarioIdAndTipoAndTitulo(medicamento.getUsuario().getId(), "ESTOQUE_BAIXO", titulo);

        if (!jaExiste) {
            EventoCalendario eventoCalendario = new EventoCalendario();
            eventoCalendario.setTitulo(titulo);
            eventoCalendario.setDescricao("Restam " + medicamento.getQuantidadeEstoque() + " unidades de " + medicamento.getNome());
            eventoCalendario.setDataHora(LocalDateTime.now());
            eventoCalendario.setTipo("ESTOQUE_BAIXO");
            eventoCalendario.setUsuario(medicamento.getUsuario());
            eventoCalendarioRepository.save(eventoCalendario);
        }
    }
}
