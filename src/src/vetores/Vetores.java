package vetores;

import java.util.Scanner;

public class Vetores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        exercicio1(sc);
        exercicio2(sc);
        exercicio3(sc);
    }


    public static void exercicio1(Scanner sc) {
        int tamanho = 0;

        System.out.println("Exercício 1");
        System.out.println("Digite o tamanho do vetor:");
        tamanho = sc.nextInt();

        int[] vetor = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = (int) (Math.random() * 100);
            System.out.println("Vetor na posição " + i + ": " + vetor[i]);
        }

        System.out.println();


        for (int a = 0; a < vetor.length; a++) {
            for (int b = a; b < vetor.length; b++) {
                if (vetor[a] > vetor[b]) {
                    int aux = vetor[a];
                    vetor[a] = vetor[b];
                    vetor[b] = aux;
                }
            }
        }

        for (int c = 0; c < vetor.length; c++) {
            System.out.println("Vetor na posição " + c + ": " + vetor[c]);
        }

        System.out.println("Digite um nome");
        String nome = sc.next();
        int vogais = 0;
        for (int j = 0; j < nome.length(); j++) {
            char letra = nome.charAt(j);
            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u' ||
                    letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U') {
                vogais++;
            }
        }
        System.out.println("O número de vogais no nome é: " + vogais);
    }

    public static void exercicio2(Scanner sc) {
        int tamanho = 0;

        System.out.println("Exercício 2");
        System.out.println("Digite o tamanho do vetor:");
        tamanho = sc.nextInt();

        int[] vetor = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            System.out.println("Digite o valor para a posição " + i + ":");
            vetor[i] = sc.nextInt();
        }

        System.out.println();

        for (int a = 0; a < vetor.length; a++) {
           if (vetor[a] % 2 == 0) {
               vetor[a] = vetor[a] * 2;
           } else {
               vetor[a] = (int) Math.pow(vetor[a], 2);
           }
        }

        for (int c = 0; c < vetor.length; c++) {
            System.out.println("Vetor na posição " + c + ": " + vetor[c]);
        }
    }

    public static void exercicio3(Scanner sc) {
        System.out.println("Exercício 3");
        System.out.println("Digite uma palavra:");
        String palavra = sc.next();
        String palavraInvertida = "";

        for (int i = palavra.length() -1; i >= 0; i--) {
            palavraInvertida = palavraInvertida.concat(String.valueOf(palavra.charAt(i)));
        }
        System.out.println("A palavra invertida é: " + palavraInvertida);
    }
}
