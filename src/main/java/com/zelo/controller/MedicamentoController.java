package com.zelo.controller;

import com.zelo.dto.MedicamentoAlarmeDTO;
import com.zelo.dto.MedicamentoDTO;
import com.zelo.entity.Medicamento;
import com.zelo.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping
    public Medicamento cadastrar(@PathVariable Long usuarioId, @RequestBody MedicamentoDTO dto) {
        return medicamentoService.cadastrar(usuarioId, dto);
    }

    @PostMapping("/completo")
    public Medicamento cadastrarComAlarme(@PathVariable Long usuarioId, @RequestBody MedicamentoAlarmeDTO dto) {
        return medicamentoService.cadastrarComAlarme(usuarioId, dto);
    }

    @GetMapping
    public List<Medicamento> listar(@PathVariable Long usuarioId) {
        return medicamentoService.listarPorUsuario(usuarioId);
    }

    @PutMapping("/{id}")
    public Medicamento atualizar(@PathVariable Long usuarioId, @PathVariable Long id, @RequestBody MedicamentoDTO dto) {
        return medicamentoService.atualizar(id, dto);
    }

    @PatchMapping("/{id}/status")
    public Medicamento alterarStatus(@PathVariable Long usuarioId, @PathVariable Long id, @RequestParam boolean ativo) {
        return medicamentoService.alterarStatus(id, ativo);
    }

    @PatchMapping("/{id}/dar-baixa")
    public Medicamento darBaixaEstoque(@PathVariable Long usuarioId, @PathVariable Long id, @RequestParam(defaultValue = "1") int quantidade) {
        return medicamentoService.darBaixaEstoque(id, quantidade);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long usuarioId, @PathVariable Long id) {
        medicamentoService.deletar(id);
    }
}