package danieltsuzuki.com.github.jpahibernate.mapper;

import danieltsuzuki.com.github.jpahibernate.dto.CategoriaAtualizarDto;
import danieltsuzuki.com.github.jpahibernate.dto.CategoriaDto;
import danieltsuzuki.com.github.jpahibernate.dto.CategoriaSalvarDto;
import danieltsuzuki.com.github.jpahibernate.model.Categoria;

public class CategoriaMapper {
    public static Categoria toEntity(CategoriaSalvarDto dto) {
        return new Categoria(dto.getId(), dto.getNome(), dto.getDescricao());
    }

    public static Categoria toEntity(CategoriaAtualizarDto dto) {
        return new Categoria(dto.getId(), dto.getNome(), dto.getDescricao());
    }

    public static Categoria toEntity(CategoriaDto dto) {
        return new Categoria(dto.getId(), dto.getNome(), dto.getDescricao());
    }

    public static CategoriaDto toDto(Categoria entity) {
        return new CategoriaDto(entity.getId(), entity.getNome(), entity.getDescricao());
    }
}
