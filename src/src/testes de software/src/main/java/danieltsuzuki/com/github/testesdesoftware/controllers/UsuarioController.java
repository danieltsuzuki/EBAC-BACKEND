package danieltsuzuki.com.github.testesdesoftware.controllers;

import danieltsuzuki.com.github.testesdesoftware.models.Usuario;
import danieltsuzuki.com.github.testesdesoftware.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.getUserById(id);
        return ResponseEntity.ok(usuario);
    }

}
