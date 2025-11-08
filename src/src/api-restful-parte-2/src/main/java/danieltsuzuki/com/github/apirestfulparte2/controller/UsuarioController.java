package danieltsuzuki.com.github.apirestfulparte2.controller;

import danieltsuzuki.com.github.apirestfulparte2.dto.UsuarioDto;
import danieltsuzuki.com.github.apirestfulparte2.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(dto));
    }

    @PostMapping("/logar")
    public ResponseEntity<String> logar(@RequestBody UsuarioDto dto) {
        return ResponseEntity.ok(usuarioService.logar(dto));
    }

}
