package heranca.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Radio extends Produto{
    private final int quantidadeEntradasUSB;
    private final int possuiLeitorDeCd;
    private final boolean eh220;

    public Radio(String codigo, String nome, String descricao, BigDecimal preco, String marca, int quantidadeEntradasUSB, int possuiLeitorDeCd, boolean eh220) {
        super(codigo, nome, descricao, preco, marca);
        this.quantidadeEntradasUSB = quantidadeEntradasUSB;
        this.possuiLeitorDeCd = possuiLeitorDeCd;
        this.eh220 = eh220;
    }

    public int getQuantidadeEntradasUSB() {
        return quantidadeEntradasUSB;
    }

    public int getPossuiLeitorDeCd() {
        return possuiLeitorDeCd;
    }

    public boolean isEh220() {
        return eh220;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Radio radio = (Radio) o;
        return quantidadeEntradasUSB == radio.quantidadeEntradasUSB && possuiLeitorDeCd == radio.possuiLeitorDeCd && eh220 == radio.eh220;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantidadeEntradasUSB, possuiLeitorDeCd, eh220);
    }

    @Override
    public String toString() {
        return this.getNome() + " {" +
                "codigo: " + this.getCodigo() +
                ", descricao: " + this.getDescricao() +
                ", preco: " + this.getPreco() +
                ", marca: " + this.getMarca() +
                "quantidadeEntradasUSB=" + quantidadeEntradasUSB +
                ", possuiLeitorDeCd=" + possuiLeitorDeCd +
                ", eh220=" + eh220 +
                '}';
    }
}
