package streamApi1;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Empresa {
    private List<Funcionario> funcionarios;
    private String nome;

    public Empresa(List<Funcionario> funcionarios, String nome) {
        this.funcionarios = funcionarios;
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(funcionarios, empresa.funcionarios) && Objects.equals(nome, empresa.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(funcionarios, nome);
    }

    @Override
    public String toString() {
        return "{" +
                "\n  funcionarios: " + funcionarios +
                "\n  nome: " + nome +
                "\n}";
    }

}
