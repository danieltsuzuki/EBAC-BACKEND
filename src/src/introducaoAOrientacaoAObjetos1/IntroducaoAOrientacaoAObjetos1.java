package introducaoAOrientacaoAObjetos1;

import java.math.BigDecimal;
import java.util.Scanner;

public class IntroducaoAOrientacaoAObjetos1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigDecimal total = BigDecimal.ZERO;
        System.out.println("Quantos carros deseja cadastrar? (max = 50)");
        int quantidadeCarros = sc.nextInt();
        if (quantidadeCarros > 50) {
            System.out.println("Quantidade máxima de carros é 50.");
        }
        Carro[] carros = new Carro[quantidadeCarros];
        for (int i = 0; i < quantidadeCarros; i++) {
            System.out.println("Digite o nome do carro " + (i + 1) + ":");
            String nome = sc.next();
            System.out.println("Digite o preço do carro " + (i + 1) + ":");
            String precoInput = sc.next();
            BigDecimal preco = new BigDecimal(precoInput);
            carros[i] = new Carro(nome, preco);
        }

        for (Carro carro : carros) {
            System.out.println("Carro: " + carro.getNome());
            total = total.add(carro.getPreco());
        }
        System.out.println("Total gasto: " + total);
        System.out.println("Quantidade de carros cadastrados: " + Carro.getQuantidade());
    }
}
