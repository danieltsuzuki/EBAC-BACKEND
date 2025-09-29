package padroesDeProjeto.model;

import java.util.List;

public class Esporte {

    private String nome;
    private List<Atleta> atletas;

    public Esporte(String nome, List<Atleta> atletas) {
        this.nome = nome;
        this.atletas = atletas;
    }

    public String getNome() {
        return nome;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public String listarAtletas() {
        StringBuilder builder = new StringBuilder();
        for (Atleta atleta : atletas) {
            builder.append(atleta.getNome()).append(", ");
        }
        builder.delete(builder.length() - 2, builder.length());
        return builder.toString();
    }

    @Override
    public String toString() {
        return "{" +
                "\n  nome: " + nome +
                "\n  atletas: " + atletas +
                "\n}";
    }
}
