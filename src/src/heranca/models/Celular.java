package heranca.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Celular extends Produto{
    private final float possuiEntradaParaFone;
    private final float polegadas;
    private final int capacidadeBateriaEmMah;

    public Celular(String codigo, String nome, String descricao, BigDecimal preco, String marca, float possuiEntradaParaFone, float polegadas, int capacidadeBateriaEmMah) {
        super(codigo, nome, descricao, preco, marca);
        this.possuiEntradaParaFone = possuiEntradaParaFone;
        this.polegadas = polegadas;
        this.capacidadeBateriaEmMah = capacidadeBateriaEmMah;
    }

    public float getPossuiEntradaParaFone() {
        return possuiEntradaParaFone;
    }

    public float getPolegadas() {
        return polegadas;
    }

    public int getCapacidadeBateriaEmMah() {
        return capacidadeBateriaEmMah;
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
                ", capacidadeBateriaEmMah=" + capacidadeBateriaEmMah +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Celular celular = (Celular) o;
        return Float.compare(possuiEntradaParaFone, celular.possuiEntradaParaFone) == 0 && Float.compare(polegadas, celular.polegadas) == 0 && capacidadeBateriaEmMah == celular.capacidadeBateriaEmMah;
    }

    @Override
    public int hashCode() {
        return Objects.hash(possuiEntradaParaFone, polegadas, capacidadeBateriaEmMah);
    }
}
