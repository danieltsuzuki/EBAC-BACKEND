package danieltsuzuki.com.github.testesdesoftware.services;

import danieltsuzuki.com.github.testesdesoftware.models.Usuario;
import danieltsuzuki.com.github.testesdesoftware.repositories.UsuarioRepository;
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

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario(1L, "Daniel")));

        Usuario usuario = usuarioService.getUserById(1L);
        assertNotNull(usuario);
        assertEquals(1L, usuario.getId());
        assertEquals("Daniel", usuario.getNome());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExistir() {
        when(usuarioRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.getUserById(2L);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(2L);
    }

}