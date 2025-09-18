package heranca.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Televisao extends Produto{
    private final float polegadas;
    private final boolean eh220;

    public Televisao(String codigo, String nome, String descricao, BigDecimal preco, String marca, float polegadas, boolean eh220) {
        super(codigo, nome, descricao, preco, marca);
        this.polegadas = polegadas;
        this.eh220 = eh220;
    }

    public float getPolegadas() {
        return polegadas;
    }

    public boolean isEh220() {
        return eh220;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Televisao televisao = (Televisao) o;
        return Float.compare(polegadas, televisao.polegadas) == 0 && eh220 == televisao.eh220;
    }

    @Override
    public int hashCode() {
        return Objects.hash(polegadas, eh220);
    }

    @Override
    public String toString() {
        return this.getNome() + " {" +
                "codigo: " + this.getCodigo() +
                ", descricao: " + this.getDescricao() +
                ", preco: " + this.getPreco() +
                ", marca: " + this.getMarca() +
                "polegadas=" + polegadas +
                ", eh220=" + eh220 +
                '}';
    }
}
