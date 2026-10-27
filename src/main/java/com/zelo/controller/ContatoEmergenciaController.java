package com.zelo.controller;

import com.zelo.dto.ContatoEmergenciaDTO;
import com.zelo.entity.ContatoEmergencia;
import com.zelo.service.ContatoEmergenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/contatos")
public class ContatoEmergenciaController {

    @Autowired
    private ContatoEmergenciaService contatoEmergenciaService;

    @PostMapping
    public ContatoEmergencia cadastrar(@PathVariable Long usuarioId, @RequestBody ContatoEmergenciaDTO dto) {
        return contatoEmergenciaService.cadastrar(usuarioId, dto);
    }

    @GetMapping
    public List<ContatoEmergencia> listar(@PathVariable Long usuarioId) {
        return contatoEmergenciaService.listarPorUsuario(usuarioId);
    }

    @PutMapping("/{id}")
    public ContatoEmergencia atualizar(@PathVariable Long usuarioId, @PathVariable Long id, @RequestBody ContatoEmergenciaDTO dto) {
        return contatoEmergenciaService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long usuarioId, @PathVariable Long id) {
        contatoEmergenciaService.deletar(id);
    }
}
