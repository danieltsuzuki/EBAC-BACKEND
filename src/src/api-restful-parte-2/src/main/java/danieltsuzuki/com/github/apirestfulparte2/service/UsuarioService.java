package danieltsuzuki.com.github.apirestfulparte2.service;

import danieltsuzuki.com.github.apirestfulparte2.dto.UsuarioDto;
import danieltsuzuki.com.github.apirestfulparte2.model.Usuario;
import danieltsuzuki.com.github.apirestfulparte2.repository.UsuarioRepository;
import danieltsuzuki.com.github.apirestfulparte2.service.exception.NotFoundException;
import danieltsuzuki.com.github.apirestfulparte2.service.exception.SessionExpiredException;
import danieltsuzuki.com.github.apirestfulparte2.service.exception.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final HttpSession httpSession;

    public UsuarioService(final UsuarioRepository usuarioRepository, final HttpSession httpSession) {
        this.usuarioRepository = usuarioRepository;
        this.httpSession = httpSession;

    }

    public String criarUsuario(UsuarioDto dto) {
        usuarioRepository.save(new Usuario(dto.getNome(), dto.getSenha()));
        return "Usuário criado com sucesso!";
    }

    public String logar(UsuarioDto dto) {
        Usuario usuario =  usuarioRepository.findByNomeAndSenha(dto.getNome(), dto.getSenha())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        httpSession.setAttribute("logados", Map.of("expiracao", LocalDateTime.now().plusMinutes(1),
                "usuario", usuario.getNome(), "senha", usuario.getSenha()));
        return dto.getNome() + " Logado com sucesso!";
    }

    public boolean logado() {
        Map<String, Object> logados = (Map<String, Object>) httpSession.getAttribute("logados");
        if (logados == null) {
            throw new UnauthorizedException("Usuário não está logado");
        }

        String nome = (String) logados.get("usuario");
        String senha = (String) logados.get("senha");
        LocalDateTime expiracao = (LocalDateTime) logados.get("expiracao");

        if (nome == null || senha == null) {
            throw new NotFoundException("Usuário ou senha inválidos");
        }

        if (LocalDateTime.now().isAfter(expiracao)) {
            throw new SessionExpiredException("Sessão expirada");
        }

        return true;
    }

}
