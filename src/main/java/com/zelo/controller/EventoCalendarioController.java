package com.zelo.controller;

import com.zelo.dto.EventoCalendarioDTO;
import com.zelo.entity.EventoCalendario;
import com.zelo.service.EventoCalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/eventos")
public class EventoCalendarioController {

    @Autowired
    private EventoCalendarioService eventoCalendarioService;

    @PostMapping
    public EventoCalendario cadastrar(@PathVariable Long usuarioId, @RequestBody EventoCalendarioDTO dto) {
        return eventoCalendarioService.cadastrar(usuarioId, dto);
    }

    @GetMapping
    public List<EventoCalendario> listar(@PathVariable Long usuarioId) {
        return eventoCalendarioService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/periodo")
    public List<EventoCalendario> listarPorPeriodo(@PathVariable Long usuarioId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return eventoCalendarioService.listarPorPeriodo(usuarioId, inicio, fim);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long usuarioId, @PathVariable Long id) {
        eventoCalendarioService.deletar(id);
    }
}
