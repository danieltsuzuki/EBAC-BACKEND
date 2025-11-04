package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.service;

import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.DB.GravadorDeDados;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.DB.LeitorDeDados;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.dto.UsuarioSalvarDto;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Usuario;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.util.exception.LivroIndisponivelException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioService {

    public void salvar(Scanner sc) {
        GravadorDeDados gravadorDeDados = new GravadorDeDados();
        gravadorDeDados.gravar(prepararParaGravar(sc));
        gravadorDeDados.fechar();
    }

    public UsuarioSalvarDto prepararParaGravar(Scanner sc) {
        System.out.println("Digite o nome do usuario: ");
        String nome = sc.nextLine();
        System.out.println("Digite o email do usuario: ");
        String email = sc.nextLine();
        return new UsuarioSalvarDto(nome, email);
    }

    public void listar() {
        LeitorDeDados leitorDeDados = new LeitorDeDados();
        try {
            List<Usuario> usuarios = leitorDeDados.listarUsuarios();
            if (usuarios.isEmpty())
                throw new LivroIndisponivelException("Nenhum usuário cadastrado.");

            for (Usuario usuario : usuarios)
                System.out.println(usuario);

            System.out.println();
        } catch (Exception e) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, e.getMessage());
        } finally {
            leitorDeDados.fechar();
        }
    }

    public Optional<Usuario> retornarPorNome(String nome) {
        LeitorDeDados leitorDeDados = new LeitorDeDados();
        Optional<Usuario> usuario = leitorDeDados.listarUsuarios().stream().filter(u -> u.getNome().equals(nome)).findFirst();
        leitorDeDados.fechar();
        return usuario;
    }
}
