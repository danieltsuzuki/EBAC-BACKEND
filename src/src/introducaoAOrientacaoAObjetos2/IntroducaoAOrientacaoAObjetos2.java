package introducaoAOrientacaoAObjetos2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IntroducaoAOrientacaoAObjetos2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Aluno> alunos = new ArrayList<>();

        System.out.println("Digite o número de alunos:");
        int numeroDeAlunos = sc.nextInt();
        System.out.println("Digite o número de notas por aluno:");
        int numeroDeNotas = sc.nextInt();

        for (int i = 0; i < numeroDeAlunos; i++) {
            System.out.println("Digite o nome do aluno " + (i + 1) + ":");
            String nome = sc.next();
            Aluno aluno = new Aluno(nome);

            for (int j = 0; j < numeroDeNotas; j++) {
                System.out.println("Digite a nota " + (j + 1) + " do aluno " + nome + ":");
                double nota = sc.nextDouble();
                if (nota < 0 || nota > 10) {
                    System.out.println("Nota inválida. Digite uma nota entre 0 e 10.");
                    j--;
                    continue;
                }
                aluno.setNota(nota);
            }

            alunos.add(aluno);
        }

        for (Aluno aluno : alunos) {
            System.out.println(aluno.toString());
        }

    }
}
