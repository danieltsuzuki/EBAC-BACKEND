package heranca.models;

import java.math.BigDecimal;

public class Produto {
    private final String codigo;
    private final String nome;
    private final String descricao;
    private final BigDecimal preco;
    private final String marca;
    public Produto(String codigo, String nome, String descricao, BigDecimal preco, String marca) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getMarca() {
        return marca;
    }

}
