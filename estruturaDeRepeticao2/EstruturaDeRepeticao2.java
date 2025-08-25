package estruturaDeRepeticao2;

import java.util.Scanner;

public class EstruturaDeRepeticao2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite um número inteiro positivo: ");
        int numero = sc.nextInt();
        int i = 1;
        while (i <= 10) {
              System.out.println(i + " * " + numero + " = " + (i * numero));
              i++;
        }
        System.out.println("Deseja continuar? (1 - Continuar/2 - Sair)");
        int opcao = sc.nextInt();
        if (opcao == 1) {
            System.out.println("Digite uma frase:");
            String frase = sc.next();
            System.out.println(frase);
        } else {
            System.out.println("Obrigado por utilizar o nosso sistema, espero que tenha gostado.");
        }

    }
}
