package danieltsuzuki.com.github.testesdesoftware.controllers;

import danieltsuzuki.com.github.testesdesoftware.models.Usuario;
import danieltsuzuki.com.github.testesdesoftware.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Long idExistente;

    @BeforeEach
    void setup() {
        Usuario usuario = new Usuario(null, "Daniel");
        usuario = usuarioRepository.save(usuario);
        idExistente = usuario.getId();
    }

    @Test
    void deveRetonarUsuarioE200Ok() throws Exception {
        mockMvc.perform(get("/usuarios/{id}", idExistente)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(idExistente.intValue())))
                .andExpect(jsonPath("$.nome", is("Daniel")));
    }

    @Test
    void deveRetornar404QuandoUsuarioNaoExistir() throws Exception {
        Long idInexistente = 999L;

        mockMvc.perform(get("/usuarios/{id}", idInexistente)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("Usuário não encontrado")));
    }

}