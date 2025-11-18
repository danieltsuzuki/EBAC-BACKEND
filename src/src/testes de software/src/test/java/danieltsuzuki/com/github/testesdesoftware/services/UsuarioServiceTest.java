package danieltsuzuki.com.github.testesdesoftware.services;

import danieltsuzuki.com.github.testesdesoftware.models.Usuario;
import danieltsuzuki.com.github.testesdesoftware.repositories.UsuarioRepository;
import danieltsuzuki.com.github.testesdesoftware.services.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private static final long ID_NAO_EXISTENTE = 999L;
    private static final long ID_EXISTENTE = 1;

    @BeforeEach
    void setUp() {
        usuario = new Usuario(ID_EXISTENTE, "Daniel");

    }

    @Test
    void deveRetornarUsuario() {
        when(usuarioRepository.findById(ID_EXISTENTE)).thenReturn(Optional.of(usuario));
        var resultado = usuarioService.getUserById(ID_EXISTENTE);
        verify(usuarioRepository, times(1)).findById(ID_EXISTENTE);
        assertEquals(usuario, resultado);
        assertEquals(usuario.getNome(), resultado.getNome());
        assertEquals(usuario.getId(), resultado.getId());
    }

    @Test
    void deveRetornarExcecaoUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(ID_NAO_EXISTENTE)).thenThrow(new UserNotFoundException("Usuário com id " + ID_NAO_EXISTENTE + " não encontrado."));
        var erro = assertThrowsExactly(UserNotFoundException.class, () -> usuarioService.getUserById(ID_NAO_EXISTENTE));
        assertEquals(mensagemUserNotFoundException(ID_NAO_EXISTENTE), erro.getMessage());
    }

    private String mensagemUserNotFoundException(Long id) {
        return "Usuário com id " + id + " não encontrado.";
    }

}