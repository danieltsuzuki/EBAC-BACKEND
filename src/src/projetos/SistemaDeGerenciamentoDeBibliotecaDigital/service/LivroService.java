package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.service;

import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.DB.GravadorDeDados;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.DB.LeitorDeDados;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.dto.LivroSalvarDto;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Livro;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.util.exception.LivroIndisponivelException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LivroService {

    public void salvar(Scanner sc) {
        GravadorDeDados gravadorDeDados = new GravadorDeDados();
        gravadorDeDados.gravar(prepararParaGravar(sc));
        gravadorDeDados.fechar();
    }

    public LivroSalvarDto prepararParaGravar(Scanner sc) {
        String nome = null;
        String autor = null;
        int ano = 0;
        try {
            System.out.println("Digite o nome do livro: ");
            nome = sc.nextLine();
            System.out.println("Digite o nome do autor: ");
            autor = sc.nextLine();
            System.out.println("Digite o ano do livro: ");
            ano = sc.nextInt();
            return new LivroSalvarDto(nome, autor, ano);
        } catch (InputMismatchException e){
            Logger.getLogger(LivroService.class.getName()).log(Level.SEVERE, "Ano inválido. Certifique-se de inserir um número inteiro para o ano.");
        } catch (Exception e) {
            Logger.getLogger(LivroService.class.getName()).log(Level.SEVERE, e.getMessage() == null ? e.toString() : e.getMessage());
        }
        sc.next();
        return null;
    }

    public void listar() {
        LeitorDeDados leitorDeDados = new LeitorDeDados();
        try {
            List<Livro> livros = leitorDeDados.listarLivros();
            if (livros.isEmpty())
                throw new LivroIndisponivelException("Nenhum livro cadastrado.");
            for (Livro livro : livros)
                System.out.println(livro);
            System.out.println();
        } catch (Exception e) {
            Logger.getLogger(LivroService.class.getName()).log(Level.SEVERE, e.getMessage());
        } finally {
            leitorDeDados.fechar();
        }
    }

    public void buscarPorTitulo(Scanner sc) {
        LeitorDeDados leitorDeDados = new LeitorDeDados();
        try {
            System.out.println("Digite o nome do livro: ");
            String titulo = sc.nextLine();
            Livro livro = leitorDeDados.listarLivros().stream().filter(
                    l -> l.getTitulo().equalsIgnoreCase(titulo)
            ).findFirst().orElseThrow(() -> new LivroIndisponivelException("Livro não encontrado"));
            System.out.println(livro != null ? livro + "\n" : "Livro não encontrado:");
        } catch (Exception e) {
            Logger.getLogger(LivroService.class.getName()).log(Level.SEVERE, e.getMessage());
        } finally {
            leitorDeDados.fechar();
        }
    }

    public List<Livro> ordenarPorTitulo() {
        LeitorDeDados leitorDeDados = new LeitorDeDados();
        List<Livro> livrosOrdenados = leitorDeDados.listarLivros().stream()
                    .sorted()
                    .collect(Collectors.toList());
        for (Livro livro : livrosOrdenados) {
            System.out.println(livro);
        }
        System.out.println();
        leitorDeDados.fechar();
        return livrosOrdenados;
    }

    public Optional<Livro> retornarPorTitulo(String titulo) {
        LeitorDeDados leitorDeDados = new LeitorDeDados();
        Optional<Livro> livro = leitorDeDados.listarLivros().stream().filter(l -> l.getTitulo().equalsIgnoreCase(titulo)).findFirst();
        leitorDeDados.fechar();
        return livro;
    }
}
