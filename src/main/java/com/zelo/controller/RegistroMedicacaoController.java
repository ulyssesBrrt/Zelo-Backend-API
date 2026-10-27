package com.zelo.controller;

import com.zelo.dto.HistoricoDiaResumoDTO;
import com.zelo.dto.HistoricoResumoDiaDTO;
import com.zelo.entity.RegistroMedicacao;
import com.zelo.service.RegistroMedicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroMedicacaoController {

    @Autowired
    private RegistroMedicacaoService registroMedicacaoService;

    @PostMapping("/{alarmeId}/confirmar")
    public RegistroMedicacao confirmar(@PathVariable Long alarmeId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHoraAgendada) {
        return registroMedicacaoService.confirmarIngestao(alarmeId, dataHoraAgendada);
    }

    @PostMapping("/{alarmeId}/ignorar")
    public RegistroMedicacao ignorar(@PathVariable Long alarmeId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHoraAgendada) {
        return registroMedicacaoService.ignorar(alarmeId, dataHoraAgendada);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<RegistroMedicacao> listarPorUsuario(@PathVariable Long usuarioId) {
        return registroMedicacaoService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/usuario/{usuarioId}/dia")
    public HistoricoResumoDiaDTO resumoDia(@PathVariable Long usuarioId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return registroMedicacaoService.obterResumoDia(usuarioId, data);
    }

    @GetMapping("/usuario/{usuarioId}/periodo")
    public List<HistoricoDiaResumoDTO> resumoPeriodo(@PathVariable Long usuarioId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return registroMedicacaoService.obterResumoPeriodo(usuarioId, inicio, fim);
    }
}
