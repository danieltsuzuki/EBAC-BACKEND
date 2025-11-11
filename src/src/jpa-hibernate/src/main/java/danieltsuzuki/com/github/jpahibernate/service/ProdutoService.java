package danieltsuzuki.com.github.jpahibernate.service;

import danieltsuzuki.com.github.jpahibernate.dto.*;
import danieltsuzuki.com.github.jpahibernate.exception.NotFoundException;
import danieltsuzuki.com.github.jpahibernate.mapper.CategoriaMapper;
import danieltsuzuki.com.github.jpahibernate.mapper.ProdutoMapper;
import danieltsuzuki.com.github.jpahibernate.model.Categoria;
import danieltsuzuki.com.github.jpahibernate.model.Produto;
import danieltsuzuki.com.github.jpahibernate.repository.CategoriaRepository;
import danieltsuzuki.com.github.jpahibernate.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public void salvar(ProdutoSalvarDto dto) {
        var produto = ProdutoMapper.toEntity(dto);
        var categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada com id: " + dto.getCategoriaId()));
        produto.setCategoria(categoria);
        produtoRepository.save(produto);
    }

    public Page<ProdutoDto> buscarPorNome(String nome, Pageable pageable) {
        List<ProdutoDto> produtos = produtoRepository.buscarProdutoPorNome(nome, pageable).stream().map(ProdutoMapper::toDto).toList();
        return  new PageImpl<ProdutoDto>(produtos, pageable, produtos.size());
    }

    public Page<ProdutoSemCategoriaDto> listar(Pageable pageable) {
        return produtoRepository.findAll(pageable).map(ProdutoMapper::toProdutoSemCategoriaDto);
    }

    public void removerPorId(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NotFoundException("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id);
    }

    public void atualizar(ProdutoAtualizarDto dto, Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado com id: " + id));
        Categoria categoria = null;

        if (dto.getCategoriaId() != null) {
            categoria = categoriaRepository.findById(dto.getCategoriaId()).orElseThrow(() -> new NotFoundException("Categoria não encontrada com id: " + dto.getCategoriaId()));
        } else {
            categoria = produto.getCategoria();
        }

        dto.setId(id);
        if (dto.getNome() == null)
            dto.setNome(produto.getNome());

        if (dto.getDescricao() == null)
            dto.setDescricao(produto.getDescricao());

        if (dto.getPreco() == null)
            dto.setPreco(produto.getPreco());

        produtoRepository.save(ProdutoMapper.toEntity(dto, categoria));
    }
}
