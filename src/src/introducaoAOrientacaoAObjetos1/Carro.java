package introducaoAOrientacaoAObjetos1;

import java.math.BigDecimal;

public class Carro {
    private String nome;
    private static int quantidade = 0;
    private BigDecimal preco;

    public Carro(String nome, BigDecimal preco) {
        this.nome = nome;
        this.preco = preco;
        quantidade++;
    }

    public String getNome() {
        return nome;
    }

    public static int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
