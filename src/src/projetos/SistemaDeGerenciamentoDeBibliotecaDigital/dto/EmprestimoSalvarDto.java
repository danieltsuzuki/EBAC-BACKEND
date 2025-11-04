package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.dto;

import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Livro;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Usuario;

public class EmprestimoSalvarDto {

    private Livro livros;
    private Usuario usuarios;

    public EmprestimoSalvarDto(Livro livros, Usuario usuarios) {
        this.livros = livros;
        this.usuarios = usuarios;
    }

    public Livro getLivros() {
        return livros;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    @Override
    public String toString() {
        return "e|" + livros.getTitulo() + "|" +usuarios.getNome();
    }
}
