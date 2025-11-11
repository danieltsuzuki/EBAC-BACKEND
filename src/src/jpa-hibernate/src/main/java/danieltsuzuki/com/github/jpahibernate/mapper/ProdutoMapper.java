package danieltsuzuki.com.github.jpahibernate.mapper;

import danieltsuzuki.com.github.jpahibernate.dto.ProdutoAtualizarDto;
import danieltsuzuki.com.github.jpahibernate.dto.ProdutoDto;
import danieltsuzuki.com.github.jpahibernate.dto.ProdutoSalvarDto;
import danieltsuzuki.com.github.jpahibernate.dto.ProdutoSemCategoriaDto;
import danieltsuzuki.com.github.jpahibernate.model.Categoria;
import danieltsuzuki.com.github.jpahibernate.model.Produto;

public class ProdutoMapper {
    public static Produto toEntity(ProdutoSalvarDto dto) {
        return new Produto(dto.getId(), dto.getNome(), dto.getDescricao(), dto.getPreco(), null);
    }

    public static Produto toEntity(ProdutoAtualizarDto dto, Categoria categoria) {
        return new Produto(dto.getId(), dto.getNome(), dto.getDescricao(), dto.getPreco(), categoria);
    }

    public static Produto toEntity(ProdutoDto dto){
        return new Produto(dto.getId(), dto.getNome(), dto.getDescricao(), dto.getPreco(),
                CategoriaMapper.toEntity(dto.getCategoria()));
    }

    public static ProdutoDto toDto(Produto entity) {
        return new ProdutoDto(entity.getId(), entity.getNome(), entity.getDescricao(), entity.getPreco(),
                CategoriaMapper.toDto(entity.getCategoria()));
    }

    public static ProdutoSemCategoriaDto toProdutoSemCategoriaDto(Produto entity) {
        return new ProdutoSemCategoriaDto(entity.getId(), entity.getNome(), entity.getDescricao(), entity.getPreco());
    }
}
