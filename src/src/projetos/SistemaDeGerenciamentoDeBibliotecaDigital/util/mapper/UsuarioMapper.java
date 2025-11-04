package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.util.mapper;

import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.dto.UsuarioSalvarDto;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Usuario;

public class UsuarioMapper {
    public static UsuarioSalvarDto toUsuarioSalvarDto(Usuario usuario) {
        return new UsuarioSalvarDto(usuario.getNome(), usuario.getEmail());
    }
}
