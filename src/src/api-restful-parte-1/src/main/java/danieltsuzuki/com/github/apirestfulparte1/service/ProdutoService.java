package danieltsuzuki.com.github.apirestfulparte1.service;

import danieltsuzuki.com.github.apirestfulparte1.dto.ProdutoEntradaDto;
import danieltsuzuki.com.github.apirestfulparte1.dto.ProdutoRetornarDto;
import danieltsuzuki.com.github.apirestfulparte1.model.Produto;
import danieltsuzuki.com.github.apirestfulparte1.service.exception.BusinessException;
import danieltsuzuki.com.github.apirestfulparte1.service.exception.NotFoundException;
import danieltsuzuki.com.github.apirestfulparte1.utils.ProdutoMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();
    private static int id = 0;

    public int inserir(ProdutoEntradaDto dto) {
        validarEntrada(dto.getNome(), dto.getPreco());
        id++;
        produtos.add(ProdutoMapper.criarProduto(dto, id));
        return id;
    }

    public void remover(int id) {
        boolean removido = produtos.removeIf(p -> p.getId() == id);
        if (!removido) {
            throw new NotFoundException("Produto com ID " + id + " não encontrado.");
        }
    }

    public List<ProdutoRetornarDto> listar() {
        return produtos.stream()
                .map(ProdutoMapper::criarProdutoRetornarDto)
                .collect(Collectors.toList());
    }

    public void atualizarParcialmente(ProdutoEntradaDto dto, int id) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                Produto produtoAntigo = produtos.get(i);
                if (dto.getNome() == null || dto.getNome().isBlank())
                    dto.setNome(produtoAntigo.getNome());

                if (dto.getPreco() == null)
                    dto.setPreco(produtoAntigo.getPreco());
                validarEntrada(dto.getNome(), dto.getPreco());
                produtos.set(i, ProdutoMapper.criarProduto(dto, produtoAntigo.getId()));
                return;
            }
        }
        throw new NotFoundException("Produto com ID " + id + " não encontrado.");
    }

    public ProdutoRetornarDto buscar(int id) {
        Produto produto = produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Produto com ID " + id + " não encontrado."));
        return ProdutoMapper.criarProdutoRetornarDto(produto);
    }

    private void validarEntrada(String nome, Double preco) {
        if (nome == null || nome.isBlank())
            throw new BusinessException("Nome do produto é obrigatório.");
        if (preco == null)
            throw new BusinessException("Preço do produto é obrigatório.");
        if (preco <= 0 )
            throw new BusinessException("Preço do produto deve ser maior que zero.");
    }

//    private int ultimoId() {
//        return produtos.stream()
//                .mapToInt(Produto::getId)
//                .max()
//                .orElse(0);
//    }


}
