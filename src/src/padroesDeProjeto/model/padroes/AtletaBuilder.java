package padroesDeProjeto.model.padroes;

import padroesDeProjeto.model.Atleta;

public class AtletaBuilder {
    private String nome;

    public static AtletaBuilder create() {
        return new AtletaBuilder();
    }

    public AtletaBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }
    public Atleta build() {
        return new Atleta(nome);
    }
}
