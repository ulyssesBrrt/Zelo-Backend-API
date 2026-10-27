package com.zelo.service;

import com.zelo.dto.CadastroUsuarioDTO;
import com.zelo.dto.ContatoEmergenciaDTO;
import com.zelo.dto.EditarPerfilDTO;
import com.zelo.dto.InformacoesSaudeDTO;
import com.zelo.dto.LoginDTO;
import com.zelo.entity.Usuario;
import com.zelo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private LocalDate converterDataNascimento(Object value) {
        if (value == null) return null;
        String dateStr = value.toString().trim();
        if (dateStr.isBlank()) return null;
        try {
            if (dateStr.contains("/")) {
                String[] parts = dateStr.split("/");
                if (parts.length == 3) {
                    int day = Integer.parseInt(parts[0]);
                    int month = Integer.parseInt(parts[1]);
                    int year = Integer.parseInt(parts[2]);
                    return LocalDate.of(year, month, day);
                }
            } else if (dateStr.contains("-")) {
                return LocalDate.parse(dateStr); // Suporta YYYY-MM-DD
            }
        } catch (Exception e) {
            System.err.println("Erro ao converter dataNascimento: " + e.getMessage());
        }
        return null;
    }

    public Usuario cadastrar(CadastroUsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setTipoUsuario(dto.getTipoUsuario() != null ? dto.getTipoUsuario() : "PACIENTE");
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> login(LoginDTO dto) {
        return usuarioRepository.findByEmailAndSenha(dto.getEmail(), dto.getSenha());
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizar(Long id, CadastroUsuarioDTO dto) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuario.setSenha(dto.getSenha());
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarParcial(Long id, Map<String, Object> dados) {
        Usuario usuario = buscarPorId(id);
        if (dados.containsKey("nome") && dados.get("nome") != null) {
            usuario.setNome((String) dados.get("nome"));
        }
        if (dados.containsKey("email") && dados.get("email") != null) {
            usuario.setEmail((String) dados.get("email"));
        }
        if (dados.containsKey("telefone") && dados.get("telefone") != null) {
            usuario.setTelefone((String) dados.get("telefone"));
        }
        if (dados.containsKey("dataNascimento") && dados.get("dataNascimento") != null) {
            LocalDate data = converterDataNascimento(dados.get("dataNascimento"));
            if (data != null) usuario.setDataNascimento(data);
        }
        if (dados.containsKey("fotoUri") && dados.get("fotoUri") != null) {
            usuario.setFotoUrl((String) dados.get("fotoUri"));
        } else if (dados.containsKey("fotoUrl") && dados.get("fotoUrl") != null) {
            usuario.setFotoUrl((String) dados.get("fotoUrl"));
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario editarPerfil(Long id, EditarPerfilDTO dto) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setTelefone(dto.getTelefone());
        usuario.setFotoUrl(dto.getFotoUrl());
        return usuarioRepository.save(usuario);
    }

    public Usuario editarInformacoesSaude(Long id, Map<String, Object> dados) {
        Usuario usuario = buscarPorId(id);

        if (dados.containsKey("tipoSanguineo") && dados.get("tipoSanguineo") != null) {
            usuario.setTipoSanguineo((String) dados.get("tipoSanguineo"));
        }
        if (dados.containsKey("alergias") && dados.get("alergias") != null) {
            usuario.setAlergias((String) dados.get("alergias"));
        }
        if (dados.containsKey("condicaoSaude") && dados.get("condicaoSaude") != null) {
            usuario.setCondicaoSaude((String) dados.get("condicaoSaude"));
        }
        if (dados.containsKey("observacoesImportantes") && dados.get("observacoesImportantes") != null) {
            usuario.setObservacoesImportantes((String) dados.get("observacoesImportantes"));
        }
        if (dados.containsKey("dataNascimento") && dados.get("dataNascimento") != null) {
            LocalDate data = converterDataNascimento(dados.get("dataNascimento"));
            if (data != null) usuario.setDataNascimento(data);
        }
        if (dados.containsKey("telefone") && dados.get("telefone") != null) {
            usuario.setTelefone((String) dados.get("telefone"));
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario salvarFoto(Long id, MultipartFile foto) {
        Usuario usuario = buscarPorId(id);
        try {
            String pastaUploads = "uploads/fotos/";
            File diretorio = new File(pastaUploads);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            String nomeArquivo = System.currentTimeMillis() + "_" + foto.getOriginalFilename();
            Path caminho = Paths.get(pastaUploads + nomeArquivo);
            Files.write(caminho, foto.getBytes());

            usuario.setFotoUrl("/uploads/fotos/" + nomeArquivo);
            return usuarioRepository.save(usuario);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar foto de perfil", e);
        }
    }

    // ----- Métodos de Contato de Emergência (SOS) -----

    public ContatoEmergenciaDTO buscarContatoEmergencia(Long userId) {
        Usuario usuario = buscarPorId(userId);
        return new ContatoEmergenciaDTO(
                usuario.getContatoNome(),
                usuario.getContatoTelefone(),
                usuario.getContatoParentesco()
        );
    }

    public ContatoEmergenciaDTO salvarContatoEmergencia(Long userId, ContatoEmergenciaDTO dto) {
        Usuario usuario = buscarPorId(userId);
        usuario.setContatoNome(dto.getNome());
        usuario.setContatoTelefone(dto.getTelefone());
        usuario.setContatoParentesco(dto.getParentesco());
        usuarioRepository.save(usuario);
        return dto;
    }

    public void removerContatoEmergencia(Long userId) {
        Usuario usuario = buscarPorId(userId);
        usuario.setContatoNome(null);
        usuario.setContatoTelefone(null);
        usuario.setContatoParentesco(null);
        usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}