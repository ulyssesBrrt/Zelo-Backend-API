package com.zelo.controller;

import com.zelo.dto.RegistroDoseDTO;
import com.zelo.entity.RegistroDose;
import com.zelo.service.RegistroDoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/historico")
public class RegistroDoseController {

    @Autowired
    private RegistroDoseService service;

    @PostMapping
    public RegistroDose registrar(@PathVariable Long usuarioId, @RequestBody RegistroDoseDTO dto) {
        return service.registrar(usuarioId, dto);
    }

    @GetMapping
    public List<RegistroDose> listar(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }
}