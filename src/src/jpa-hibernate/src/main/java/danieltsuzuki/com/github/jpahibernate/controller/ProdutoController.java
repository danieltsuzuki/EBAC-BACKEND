package danieltsuzuki.com.github.jpahibernate.controller;

import danieltsuzuki.com.github.jpahibernate.dto.ProdutoAtualizarDto;
import danieltsuzuki.com.github.jpahibernate.dto.ProdutoDto;
import danieltsuzuki.com.github.jpahibernate.dto.ProdutoSalvarDto;
import danieltsuzuki.com.github.jpahibernate.dto.ProdutoSemCategoriaDto;
import danieltsuzuki.com.github.jpahibernate.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody ProdutoSalvarDto dto) {
        produtoService.salvar(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoSemCategoriaDto>> listar(@RequestParam(defaultValue = "0") int pagina,
                                                               @RequestParam(defaultValue = "20") int tamanho,
                                                               @RequestParam(defaultValue = "id") String ordenarPor,
                                                               @RequestParam(defaultValue = "asc") String direcao) {
        Sort sort = Sort.by(direcao.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, ordenarPor);
        PageRequest p = PageRequest.of(pagina, tamanho, sort);
        return ResponseEntity.ok(produtoService.listar(p));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<ProdutoDto>> buscarPorNome(@RequestParam String nome,
                                                          @RequestParam(defaultValue = "0") int pagina,
                                                          @RequestParam(defaultValue = "20") int tamanho,
                                                          @RequestParam(defaultValue = "id") String ordenarPor,
                                                          @RequestParam(defaultValue = "asc") String direcao) {
        Sort sort = Sort.by(direcao.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, ordenarPor);
        PageRequest p = PageRequest.of(pagina, tamanho, sort);
        return ResponseEntity.ok(produtoService.buscarPorNome(nome, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        produtoService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizar(@Valid @RequestBody ProdutoAtualizarDto dto, @PathVariable Long id) {
        produtoService.atualizar(dto, id);
        return ResponseEntity.ok().build();
    }
}
