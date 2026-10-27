package com.zelo.controller;

import com.zelo.dto.AlarmeDTO;
import com.zelo.entity.Alarme;
import com.zelo.service.AlarmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarmes")
public class AlarmeController {

    @Autowired
    private AlarmeService alarmeService;

    @PostMapping
    public Alarme cadastrar(@RequestBody AlarmeDTO dto) {
        return alarmeService.cadastrar(dto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Alarme> listarAtivosPorUsuario(@PathVariable Long usuarioId) {
        return alarmeService.listarAtivosPorUsuario(usuarioId);
    }

    @GetMapping("/usuario/{usuarioId}/proximo")
    public ResponseEntity<Alarme> buscarProximo(@PathVariable Long usuarioId) {
        return alarmeService.buscarProximoAlarme(usuarioId).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/medicamento/{medicamentoId}")
    public List<Alarme> listarPorMedicamento(@PathVariable Long medicamentoId) {
        return alarmeService.listarPorMedicamento(medicamentoId);
    }

    @PutMapping("/{id}")
    public Alarme atualizar(@PathVariable Long id, @RequestBody AlarmeDTO dto) {
        return alarmeService.atualizar(id, dto);
    }

    @PatchMapping("/{id}/status")
    public Alarme alterarStatus(@PathVariable Long id, @RequestParam boolean ativo) {
        return alarmeService.alterarStatus(id, ativo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alarmeService.deletar(id);
    }
}
