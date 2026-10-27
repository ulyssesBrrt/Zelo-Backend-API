package com.zelo.controller;

import com.zelo.dto.AgendamentoDTO;
import com.zelo.entity.Agendamento;
import com.zelo.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Agendamento>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<Agendamento> agendamentos = service.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(agendamentos);
    }

    @PostMapping
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoDTO dto) {
        Agendamento salvo = service.criarAgendamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}