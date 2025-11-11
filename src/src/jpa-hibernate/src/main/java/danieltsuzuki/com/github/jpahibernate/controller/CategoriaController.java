package danieltsuzuki.com.github.jpahibernate.controller;

import danieltsuzuki.com.github.jpahibernate.dto.CategoriaAtualizarDto;
import danieltsuzuki.com.github.jpahibernate.dto.CategoriaDto;
import danieltsuzuki.com.github.jpahibernate.dto.CategoriaSalvarDto;
import danieltsuzuki.com.github.jpahibernate.service.CategoriaService;
import jakarta.persistence.criteria.Order;
import jakarta.validation.Valid;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody CategoriaSalvarDto dto) {
        categoriaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaDto>> listar(@RequestParam(defaultValue = "0") int pagina,
                                                    @RequestParam(defaultValue = "20") int tamanho,
                                                     @RequestParam(defaultValue = "id") String ordenarPor,
                                                     @RequestParam(defaultValue = "asc") String direcao) {
        Sort sort = Sort.by(direcao.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, ordenarPor);
        PageRequest p = PageRequest.of(pagina, tamanho, sort);
        var categorias = categoriaService.listar(p);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> detalhar(@PathVariable Long id) {
        var categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizar(@Valid @RequestBody CategoriaAtualizarDto dto, @PathVariable Long id) {
        categoriaService.atualizar(dto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        categoriaService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }

}
