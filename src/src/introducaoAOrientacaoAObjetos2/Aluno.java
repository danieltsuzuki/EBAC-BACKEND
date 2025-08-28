package introducaoAOrientacaoAObjetos2;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private List<Double> notas;

    public Aluno(String nome) {
        this.nome = nome;
        this.notas = new ArrayList<Double>();
    }

    public String getNome() {
        return nome;
    }

    public List<Double> getNotas() {
        return this.notas;
    }

    public Double getMedia() {
        return this.notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public void setNota(Double nota) {
        try {
            validaNota(nota);
            notas.add(nota);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Aluno: " + this.nome + ", Média: " + String.format("%.2f", this.getMedia());
    }

    public void validaNota(Double nota) {
        if(nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota inválida. A nota deve estar entre 0 e 10.");
        }
    }

}
