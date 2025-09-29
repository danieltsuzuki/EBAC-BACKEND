package padroesDeProjeto.model;

public class Atleta {
    private String nome;

    public Atleta(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "{" +
                "\n  nome: " + nome +
                "\n}";
    }
}
