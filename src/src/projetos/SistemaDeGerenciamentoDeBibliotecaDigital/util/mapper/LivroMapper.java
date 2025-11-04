package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.util.mapper;

import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.dto.LivroSalvarDto;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Livro;

public class LivroMapper {
    public static LivroSalvarDto toLivroSalvarDto(Livro livro) {
        return new LivroSalvarDto(livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao());
    }
}
