package exceptions;

import java.util.Scanner;

public class CadastroUsuario {

    public static void cadastrarUsuario(String nome, int idade) throws IdadeInvalidaException {
        if (idade < 18) {
            throw new IdadeInvalidaException("Usuário menor de idade");
        }
        System.out.println("Usuário " + nome + " foi cadastrado com sucesso");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Digite seu nome: ");
            String nome = sc.nextLine();

            System.out.println("Digite sua idade: ");
            int idade = sc.nextInt();

            cadastrarUsuario(nome, idade);
        } catch (IdadeInvalidaException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Processo de cadastro finalizado");
        }


    }
}
