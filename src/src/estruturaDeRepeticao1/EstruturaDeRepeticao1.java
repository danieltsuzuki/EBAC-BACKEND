package estruturaDeRepeticao1;

import java.util.Scanner;

public class EstruturaDeRepeticao1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine();
        System.out.println("Digite um número positivo:");
        int numero = sc.nextInt();
        for (int i = 0; i <= numero; i++) {
            System.out.println(i);
        }
        for(int i = numero; i >= 0; i--) {
            System.out.println(i);
        }
        if (nome.length() > 6) {
            for (int i = 0; i < numero; i++) {
                System.out.println(nome);
            }
        } else {
            System.out.println(nome);
        }
    }
}
