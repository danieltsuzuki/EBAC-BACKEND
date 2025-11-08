package danieltsuzuki.com.github.apirestfulparte2.controller;

import danieltsuzuki.com.github.apirestfulparte2.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {
    private final UsuarioService usuarioService;

    public TesteController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String teste() {
        usuarioService.logado();
        return "API RESTful parte 2 funcionando!";
    }
}
