package projetos.SistemaDeGerenciamentoDeBibliotecaDigital;

import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.service.EmprestimoService;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.service.LivroService;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.service.UsuarioService;

import java.util.Scanner;

public class Sistema {
    static char opcao;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        while (opcao != '0') {
            System.out.println("\n1 - Livros\n2 - Usuários\n3 - Emprestimos\n4 - Sair\n");
            System.out.println("Digite a opção desejada: ");
            opcao = sc.next().charAt(0);
            sc.nextLine();
            switch (opcao) {
                case '1':
                    menuLivros();
                    break;
                case '2':
                    menuUsuarios();
                    break;
                case '3':
                    menuEmprestimos();
                    break;
                case '4':
                    System.out.println("Encerrando o programa!");
                    opcao = '0';
                    break;
                default:
                    System.out.println("Opcao invalida, Tente novamente!\n");
                    menuPrincipal();
            }
        }
    }

    private static void menuLivros() {
        LivroService livroService = new LivroService();
        while (opcao != '0') {
            System.out.println("1 - Cadastrar Livro\n2 - Listar livros\n3 - Buscar por título\n4 - Ordenar por título\n5 - Voltar");
            opcao = sc.next().charAt(0);
            sc.nextLine();
            switch (opcao) {
                case '1':
                    livroService.salvar(sc);
                    break;
                case '2':
                    livroService.listar();
                    break;
                case '3':
                    livroService.buscarPorTitulo(sc);
                    break;
                case '4':
                    livroService.ordenarPorTitulo();
                    break;
                case '5':
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    menuLivros();
            }
        }
    }

    private static void menuUsuarios() {
        while (opcao != '0') {
            UsuarioService usuarioService = new UsuarioService();
            System.out.println("1 - Cadastrar Usuários\n2 - Listar Usuários\n3 - Voltar");
            opcao = sc.next().charAt(0);
            sc.nextLine();
            switch (opcao) {
                case '1':
                    usuarioService.salvar(sc);
                    break;
                case '2':
                    usuarioService.listar();
                    break;
                case '3':
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    menuUsuarios();
            }
        }
    }

    private static void menuEmprestimos() {
        EmprestimoService emprestimoService = new EmprestimoService();
        while (opcao != '0') {
            System.out.println("1 - Cadastrar emprestimo\n2 - Listar emprestimos\n3 - Devolver livro\n4 - Voltar");
            opcao = sc.next().charAt(0);
            sc.nextLine();
            switch (opcao) {
                case '1':
                    emprestimoService.salvar(sc);
                    break;
                case '2':
                    emprestimoService.listar();
                    break;
                case '3':
                    emprestimoService.devolverLivro(sc);
                    break;
                case '4':
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    menuUsuarios();
            }
        }
    }

}
