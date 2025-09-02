package recursividade;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o tamanho da escada em degraus:");
        int tamanhoEscadaEmDegraus = sc.nextInt();
        new Recursao(tamanhoEscadaEmDegraus);
    }
}
