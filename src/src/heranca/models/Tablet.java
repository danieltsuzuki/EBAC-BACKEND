package heranca.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Tablet extends Produto{
    private final boolean possuiEntradaParaFone;
    private final float polegadas;

    public Tablet(String codigo, String nome, String descricao, BigDecimal preco, String marca, boolean possuiEntradaParaFone, float polegadas) {
        super(codigo, nome, descricao, preco, marca);
        this.possuiEntradaParaFone = possuiEntradaParaFone;
        this.polegadas = polegadas;
    }

    public boolean isPossuiEntradaParaFone() {
        return possuiEntradaParaFone;
    }

    public float getPolegadas() {
        return polegadas;
    }

    @Override
    public String toString() {
        return this.getNome() + " {" +
                "codigo: " + this.getCodigo() +
                ", descricao: " + this.getDescricao() +
                ", preco: " + this.getPreco() +
                ", marca: " + this.getMarca() +
                "possuiEntradaParaFone=" + possuiEntradaParaFone +
                ", polegadas=" + polegadas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tablet tablet = (Tablet) o;
        return possuiEntradaParaFone == tablet.possuiEntradaParaFone && Float.compare(polegadas, tablet.polegadas) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(possuiEntradaParaFone, polegadas);
    }
}
