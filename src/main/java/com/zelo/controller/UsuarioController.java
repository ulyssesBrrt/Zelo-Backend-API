package com.zelo.controller;

import com.zelo.dto.CadastroUsuarioDTO;
import com.zelo.dto.ContatoEmergenciaDTO;
import com.zelo.dto.EditarPerfilDTO;
import com.zelo.dto.InformacoesSaudeDTO;
import com.zelo.entity.Usuario;
import com.zelo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Usuario atualizar(@PathVariable Long id, @RequestBody Map<String, Object> dados) {
        return usuarioService.atualizarParcial(id, dados);
    }

    @RequestMapping(value = "/{id}/perfil", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Usuario editarPerfil(@PathVariable Long id, @RequestBody EditarPerfilDTO dto) {
        return usuarioService.editarPerfil(id, dto);
    }

    @RequestMapping(value = "/{id}/saude", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Usuario editarInformacoesSaude(@PathVariable Long id, @RequestBody Map<String, Object> dados) {
        return usuarioService.editarInformacoesSaude(id, dados);
    }

    @PostMapping("/{id}/foto")
    public Usuario uploadFoto(@PathVariable Long id, @RequestParam("foto") MultipartFile foto) {
        return usuarioService.salvarFoto(id, foto);
    }

    // ----- Endpoints para Contato de Emergência (SOS) -----

    @GetMapping("/{userId}/contato-emergencia")
    public ContatoEmergenciaDTO getContatoEmergencia(@PathVariable Long userId) {
        return usuarioService.buscarContatoEmergencia(userId);
    }

    @PostMapping("/{userId}/contato-emergencia")
    public ContatoEmergenciaDTO salvarContatoEmergencia(@PathVariable Long userId, @RequestBody ContatoEmergenciaDTO dto) {
        return usuarioService.salvarContatoEmergencia(userId, dto);
    }

    @DeleteMapping("/{userId}/contato-emergencia")
    public void removerContatoEmergencia(@PathVariable Long userId) {
        usuarioService.removerContatoEmergencia(userId);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
    }
}