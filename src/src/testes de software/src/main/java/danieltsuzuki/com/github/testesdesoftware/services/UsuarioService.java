package danieltsuzuki.com.github.testesdesoftware.services;

import danieltsuzuki.com.github.testesdesoftware.models.Usuario;
import danieltsuzuki.com.github.testesdesoftware.repositories.UsuarioRepository;
import danieltsuzuki.com.github.testesdesoftware.services.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario getUserById(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário com id " + id +
                " não encontrado."));
    }
}
