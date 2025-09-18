package heranca.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Videogame extends Produto{
    private final boolean eh220;
    private final int quantidadeControlesNaCaixa;

    public Videogame(String codigo, String nome, String descricao, BigDecimal preco, String marca, boolean eh220, int quantidadeControlesNaCaixa) {
        super(codigo, nome, descricao, preco, marca);
        this.eh220 = eh220;
        this.quantidadeControlesNaCaixa = quantidadeControlesNaCaixa;
    }

    public boolean isEh220() {
        return eh220;
    }

    public int getQuantidadeControlesNaCaixa() {
        return quantidadeControlesNaCaixa;
    }

    @Override
    public String toString() {
        return this.getNome() + " {" +
                "codigo: " + this.getCodigo() +
                ", descricao: " + this.getDescricao() +
                ", preco: " + this.getPreco() +
                ", marca: " + this.getMarca() +
                "eh220=" + eh220 +
                ", quantidadeControlesNaCaixa=" + quantidadeControlesNaCaixa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Videogame videogame = (Videogame) o;
        return eh220 == videogame.eh220 && quantidadeControlesNaCaixa == videogame.quantidadeControlesNaCaixa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eh220, quantidadeControlesNaCaixa);
    }
}
