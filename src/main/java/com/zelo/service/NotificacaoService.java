package com.zelo.service;

import com.zelo.dto.NotificacaoDTO;
import com.zelo.entity.Notificacao;
import com.zelo.entity.Usuario;
import com.zelo.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    private NotificacaoDTO transformarDTO(Notificacao n) {
        return new NotificacaoDTO(n.getId(), n.getTitulo(), n.getDescricao(), n.getDataHora(), n.getTipo(), n.getLida());
    }

    private void criar(Usuario usuario, String titulo, String descricao, String tipo, LocalDateTime dataHora) {
        Notificacao notificacao = new Notificacao();
        notificacao.setTitulo(titulo);
        notificacao.setDescricao(descricao);
        notificacao.setDataHora(dataHora);
        notificacao.setTipo(tipo);
        notificacao.setLida(false);
        notificacao.setUsuario(usuario);
        notificacaoRepository.save(notificacao);
    }

    public List<NotificacaoDTO> listarPorUsuario(Long usuarioId) {
        return notificacaoRepository.findByUsuarioIdOrderByDataHoraDesc(usuarioId).stream().map(this::transformarDTO).toList();
    }

    public long contarNaoLidas(Long usuarioId) {
        return notificacaoRepository.countByUsuarioIdAndLidaFalse(usuarioId);
    }

    public Notificacao marcarComoLida(Long id) {
        Notificacao notificacao = notificacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        notificacao.setLida(true);
        return notificacaoRepository.save(notificacao);
    }

    public void marcarTodasComoLidas(Long usuarioId) {
        List<Notificacao> notificacoes = notificacaoRepository.findByUsuarioIdAndLidaFalse(usuarioId);
        notificacoes.forEach(n -> n.setLida(true));
        notificacaoRepository.saveAll(notificacoes);
    }

    public void notificarEstoqueBaixo(Usuario usuario, String nomeMedicamento, int quantidadeRestante) {
        String titulo = "Estoque Baixo";
        String descricao = "Restam " + quantidadeRestante + " comprimidos de " + nomeMedicamento;
        boolean jaExiste = notificacaoRepository.existsByUsuarioIdAndTipoAndTituloAndLidaFalse(usuario.getId(), "ESTOQUE_BAIXO", titulo);

        if (!jaExiste) {
            criar(usuario, titulo, descricao, "ESTOQUE_BAIXO", LocalDateTime.now());
        }
    }

    public void notificarEvento(Usuario usuario, String tituloEvento, String tipoEvento, LocalDateTime dataHoraEvento) {
        String descricao = dataHoraEvento.toLocalTime() + " - " + tituloEvento;
        criar(usuario, tituloEvento.equals("EXAME") ? "Exame" : "Consulta", descricao, tipoEvento, dataHoraEvento);
    }

    public void notificarHoraMedicacao(Usuario usuario, String nomeMedicamento, LocalDateTime dataHoraAlarme) {
        String descricao = String.format("%02d:%02d", dataHoraAlarme.getHour(), dataHoraAlarme.getMinute()) + " - " + nomeMedicamento;
        criar(usuario, "Hora da medicação", descricao, "MEDICACAO", dataHoraAlarme);
    }
}