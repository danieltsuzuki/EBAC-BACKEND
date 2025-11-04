package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.service;

import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.DB.GravadorDeDados;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.DB.LeitorDeDados;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.dto.EmprestimoSalvarDto;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Emprestimo;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Livro;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Usuario;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.util.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmprestimoService {
    public void salvar(Scanner sc) {
        GravadorDeDados gravadorDeDados = new GravadorDeDados();
        gravadorDeDados.gravar(prepararParaGravar(sc));
        gravadorDeDados.fechar();
    }

    public EmprestimoSalvarDto prepararParaGravar(Scanner sc) {
        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();
        try {
            System.out.println("Digite o nome do livro: ");
            String titulo = sc.nextLine();
            Optional<Livro> livro = livroService.retornarPorTitulo(titulo);
            validaLivroEncontrado (livro);

            System.out.println("Digite o nome do usuário: ");
            String nomeUsuario = sc.nextLine();
            Optional<Usuario> usuario  = usuarioService.retornarPorNome(nomeUsuario);
            validaUsuarioEncontrado (usuario);

            validaEmprestimoExistente(titulo, nomeUsuario);

            return new EmprestimoSalvarDto(livro.get(), usuario.get());
        } catch (Exception e) {
            Logger.getLogger(EmprestimoService.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public void listar() {
        LeitorDeDados leitorDeDados = new LeitorDeDados();
        try {
            List<Emprestimo> emprestimos = leitorDeDados.listarEmprestimos();
            validaListaEmprestimosVazia(emprestimos);

            for (Emprestimo emprestimo : emprestimos)
                System.out.println(emprestimo);
            System.out.println();
        } catch (Exception e) {
            Logger.getLogger(EmprestimoService.class.getName()).log(Level.SEVERE, e.getMessage());
        } finally {
            leitorDeDados.fechar();
        }
    }

    public boolean verificarEmprestimoExistente(String titulo, String nomeUsuario) {
        LeitorDeDados leitorDeDados = new LeitorDeDados();
        boolean exists = leitorDeDados.listarEmprestimos().stream().anyMatch(
                e -> e.getLivro().getTitulo().equalsIgnoreCase(titulo) &&
                     e.getUsuario().getNome().equalsIgnoreCase(nomeUsuario)
        );
        leitorDeDados.fechar();
        return exists;
    }

    public void devolverLivro(Scanner sc) {
        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();
        LeitorDeDados leitorDeDados = new LeitorDeDados();
        GravadorDeDados gravadorDeDados = new GravadorDeDados();
        try {
            List<Emprestimo> emprestimos = leitorDeDados.listarEmprestimos();
            validaExisteEmprestimo(emprestimos);

            System.out.println("Digite o nome do livro: ");
            String titulo = sc.nextLine();
            Optional<Livro> livro = livroService.retornarPorTitulo(titulo);
            validaLivroEncontrado (livro);

            System.out.println("Digite o nome do usuário: ");
            String nomeUsuario = sc.nextLine();
            Optional<Usuario> usuario  = usuarioService.retornarPorNome(nomeUsuario);
            validaUsuarioEncontrado (usuario);

            validaEmprestimoNaoExistente(titulo, nomeUsuario);

            List<String> dadosAtualizados = leitorDeDados.removerLinhaSelecionada("e|" + titulo + "|" + nomeUsuario);
            gravadorDeDados.editarArquivo(dadosAtualizados);
            System.out.println("Livro devolvido com sucesso!");
        } catch (Exception e) {
            Logger.getLogger(EmprestimoService.class.getName()).log(Level.SEVERE, e.getMessage());
        } finally {
            leitorDeDados.fechar();
            gravadorDeDados.fechar();
        }
    }

    private void validaExisteEmprestimo(List<Emprestimo> emprestimos) {
        if (emprestimos.isEmpty())
            throw new NotFoundException("Nenhum empréstimo encontrado.");
    }

    private void validaLivroEncontrado(Optional<Livro> livro) {
        if (livro.isEmpty())
            throw new NotFoundException("Livro não encontrado.");
    }

    private void validaUsuarioEncontrado(Optional<Usuario> usuario) {
        if (usuario.isEmpty())
            throw new NotFoundException("Usuário não encontrado.");
    }

    private void validaEmprestimoNaoExistente(String titulo, String nomeUsuario) {
        if (!verificarEmprestimoExistente(titulo, nomeUsuario))
            throw new NotFoundException("Empréstimo não encontrado.");
    }

    private void validaEmprestimoExistente(String titulo, String nomeUsuario) {
        if (verificarEmprestimoExistente(titulo, nomeUsuario))
            throw new IllegalArgumentException("Empréstimo já existente para este livro e usuário.");
    }

    private void validaListaEmprestimosVazia(List<Emprestimo> emprestimos) {
        if (emprestimos.isEmpty())
            throw new NotFoundException("Nenhum empréstimo encontrado.");
    }

}
