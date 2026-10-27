package com.zelo.controller;

import com.zelo.dto.NotificacaoDTO;
import com.zelo.entity.Notificacao;
import com.zelo.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping
    public List<NotificacaoDTO> listar(@PathVariable Long usuarioId) {
        return notificacaoService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/contagem")
    public Map<String, Long> contarNaoLidas(@PathVariable Long usuarioId) {
        return Map.of("naoLidas", notificacaoService.contarNaoLidas(usuarioId));
    }

    @PatchMapping("/{id}/lida")
    public Notificacao marcarComoLida(@PathVariable Long usuarioId, @PathVariable Long id) {
        return notificacaoService.marcarComoLida(id);
    }

    @PatchMapping("/ler-todas")
    public void marcarTodasComoLidas(@PathVariable Long usuarioId) {
        notificacaoService.marcarTodasComoLidas(usuarioId);
    }
}