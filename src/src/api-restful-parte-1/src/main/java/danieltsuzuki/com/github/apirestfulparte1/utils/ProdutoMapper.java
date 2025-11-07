package danieltsuzuki.com.github.apirestfulparte1.utils;

import danieltsuzuki.com.github.apirestfulparte1.dto.ProdutoEntradaDto;
import danieltsuzuki.com.github.apirestfulparte1.dto.ProdutoRetornarDto;
import danieltsuzuki.com.github.apirestfulparte1.model.Produto;

public class ProdutoMapper {
    public static Produto criarProduto(ProdutoEntradaDto dto, int id) {
        return new Produto(id, dto.getNome(), dto.getPreco());
    }


    public static ProdutoRetornarDto criarProdutoRetornarDto(Produto produto) {
        return new ProdutoRetornarDto(produto.getId(), produto.getNome(), produto.getPreco());
    }

}
