package com.zelo.controller;

import com.zelo.dto.CadastroUsuarioDTO;
import com.zelo.dto.LoginDTO;
import com.zelo.entity.Usuario;
import com.zelo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem (Vercel)
@RequestMapping({"/api/auth", ""}) // Aceita chamadas com ou sem /api/auth
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping({"/cadastro", "/api/auth/cadastro"})
    public ResponseEntity<Usuario> cadastrar(@RequestBody CadastroUsuarioDTO dto) {
        Usuario novoUsuario = usuarioService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PostMapping({"/login", "/api/auth/login"})
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        Optional<Usuario> usuario = usuarioService.login(dto);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
        }
    }
}