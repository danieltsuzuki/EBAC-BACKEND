package padroesDeProjeto.model.padroes;

import padroesDeProjeto.model.Atleta;
import padroesDeProjeto.model.Basquete;
import padroesDeProjeto.model.Esporte;
import padroesDeProjeto.model.Futebol;

import java.util.List;

public class EsporteFactory {

    public Esporte criarEsporte(String nome, List<Atleta> atletas, Class clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Classe não encontrada!");
        }
        if (clazz.equals(Futebol.class)) {
            return new Futebol(nome, atletas);
        }
        return new Basquete(nome, atletas);
    }
}
