package danieltsuzuki.com.github.apirestfulparte1.controller;

import danieltsuzuki.com.github.apirestfulparte1.dto.ProdutoEntradaDto;
import danieltsuzuki.com.github.apirestfulparte1.dto.ProdutoRetornarDto;
import danieltsuzuki.com.github.apirestfulparte1.model.Produto;
import danieltsuzuki.com.github.apirestfulparte1.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(final ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrarProduto(@RequestBody ProdutoEntradaDto dto){
        return ResponseEntity.created(URI.create("produtos/" + produtoService.inserir(dto))).body("Produto cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<ProdutoRetornarDto>> listarProdutos(){
        return ResponseEntity.ok(produtoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRetornarDto> buscarProduto(@PathVariable int id) {
        return ResponseEntity.ok(produtoService.buscar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerProduto(@PathVariable int id){
        produtoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizarProdutoParcialmente(@RequestBody ProdutoEntradaDto dto, @PathVariable int id) {
        produtoService.atualizarParcialmente(dto, id);
        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }
}
