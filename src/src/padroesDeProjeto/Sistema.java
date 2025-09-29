package padroesDeProjeto;

import padroesDeProjeto.model.Atleta;
import padroesDeProjeto.model.Basquete;
import padroesDeProjeto.model.Esporte;
import padroesDeProjeto.model.Futebol;
import padroesDeProjeto.model.padroes.AtletaBuilder;
import padroesDeProjeto.model.padroes.EsporteFactory;
import padroesDeProjeto.model.padroes.PraticarBasquete;
import padroesDeProjeto.model.padroes.PraticarFutebol;

import java.util.List;

public class Sistema {
    public static void main(String[] args) {
        Atleta daniel = AtletaBuilder.create().nome("Daniel").build();
        Atleta luke = AtletaBuilder.create().nome("Luke").build();
        Esporte basquete = new EsporteFactory().criarEsporte("Basquete", List.of(daniel, luke), Basquete.class);

        Atleta adrielly = AtletaBuilder.create().nome("Adrielly").build();
        Atleta bob = AtletaBuilder.create().nome("Bob").build();
        Esporte futebol = new EsporteFactory().criarEsporte("Futebol", List.of(adrielly, bob), Futebol.class);

        new PraticarFutebol().praticar(futebol);
        new PraticarBasquete().praticar(basquete);
    }
}
