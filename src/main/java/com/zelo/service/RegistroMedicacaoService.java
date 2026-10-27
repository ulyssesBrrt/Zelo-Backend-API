package com.zelo.service;

import com.zelo.dto.HistoricoDiaResumoDTO;
import com.zelo.dto.HistoricoItemDTO;
import com.zelo.dto.HistoricoResumoDiaDTO;
import com.zelo.entity.Alarme;
import com.zelo.entity.RegistroMedicacao;
import com.zelo.repository.AlarmeRepository;
import com.zelo.repository.RegistroMedicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroMedicacaoService {

    @Autowired
    private RegistroMedicacaoRepository registroMedicacaoRepository;

    @Autowired
    private AlarmeRepository alarmeRepository;

    @Autowired
    private AlarmeService alarmeService;

    @Autowired
    private MedicamentoService medicamentoService;

    public RegistroMedicacao confirmarIngestao(Long alarmeId, LocalDateTime dataHoraAgendada) {
        Alarme alarme = alarmeService.buscarPorId(alarmeId);
        RegistroMedicacao registroMedicacao = new RegistroMedicacao();
        registroMedicacao.setDataHoraAgendada(dataHoraAgendada);
        registroMedicacao.setDataHoraAcao(LocalDateTime.now());
        registroMedicacao.setStatus("TOMADO");
        registroMedicacao.setAlarme(alarme);
        medicamentoService.darBaixaEstoque(alarme.getMedicamento().getId(), 1);
        return registroMedicacaoRepository.save(registroMedicacao);
    }

    public RegistroMedicacao ignorar(Long alarmeId, LocalDateTime dataHoraAgendada) {
        Alarme alarme = alarmeService.buscarPorId(alarmeId);
        RegistroMedicacao registroMedicacao = new RegistroMedicacao();
        registroMedicacao.setDataHoraAgendada(dataHoraAgendada);
        registroMedicacao.setDataHoraAcao(LocalDateTime.now());
        registroMedicacao.setStatus("IGNORADO");
        registroMedicacao.setAlarme(alarme);
        return registroMedicacaoRepository.save(registroMedicacao);
    }

    public List<RegistroMedicacao> listarPorUsuario(Long usuarioId) {
        return registroMedicacaoRepository.findByAlarmeMedicamentoUsuarioIdOrderByDataHoraAgendadaDesc(usuarioId);
    }

    public HistoricoResumoDiaDTO obterResumoDia(Long usuarioId, LocalDate dia) {
        List<Alarme> alarmesAtivos = alarmeRepository.findByMedicamentoUsuarioIdAndAtivoTrue(usuarioId);

        LocalDateTime inicio = dia.atStartOfDay();
        LocalDateTime fim = dia.atTime(LocalTime.MAX);
        List<RegistroMedicacao> registroDoDia = registroMedicacaoRepository.findByAlarmeMedicamentoUsuarioIdAndDataHoraAgendadaBetween(usuarioId, inicio, fim);

        List<HistoricoItemDTO> itens = new ArrayList<>();
        int tomados = 0;

        for (Alarme alarme : alarmesAtivos) {
            Optional<RegistroMedicacao> registroMedicacao = registroDoDia.stream().filter(r -> r.getAlarme().getId().equals(alarme.getId())).findFirst();

            String status = registroMedicacao.map(RegistroMedicacao::getStatus).orElse("PENDENTE");
            if ("TOMADO".equals(status)) tomados++;

            itens.add(new HistoricoItemDTO(alarme.getMedicamento().getNome(), alarme.getMedicamento().getDosagem(), alarme.getHora(), status));
        }

        int pendentes = (int) itens.stream().filter(i -> "PENDENTE".equals(i.getStatus())).count();

        return new HistoricoResumoDiaDTO(dia, tomados, pendentes, itens);
    }

    public List<HistoricoDiaResumoDTO> obterResumoPeriodo(Long usuarioId, LocalDate inicio, LocalDate fim) {
        List<HistoricoDiaResumoDTO> resultado = new ArrayList<>();
        LocalDate atual = inicio;

        while (!atual.isAfter(fim)) {
            HistoricoResumoDiaDTO resumoDia = obterResumoDia(usuarioId, atual);
            resultado.add(new HistoricoDiaResumoDTO(atual, resumoDia.getTomados(), resumoDia.getPendentes()));
            atual = atual.plusDays(1);
        }

        return resultado;
    }
}
