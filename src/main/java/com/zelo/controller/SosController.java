package com.zelo.controller;

import com.zelo.dto.ContatoSosDTO;
import com.zelo.service.SosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sos")
public class SosController {

    @Autowired
    private SosService sosService;

    @GetMapping("/{usuarioId}")
    public List<ContatoSosDTO> acionar(@PathVariable Long usuarioId) {
        return sosService.obterContatosParaAcionamento(usuarioId);
    }
}
