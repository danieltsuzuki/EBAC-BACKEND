package streamApi1;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Funcionario {
    private String nome;
    private BigDecimal salario;
    private LocalDate dataNascimento;
    private Integer idade;

    public Funcionario(String nome, BigDecimal salario, LocalDate dataNascimento) {
        this.nome = nome;
        this.salario = salario;
        this.dataNascimento = dataNascimento;
        this.idade = LocalDate.now().getYear() - dataNascimento.getYear();
    }

    public Integer getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(nome, that.nome) && Objects.equals(salario, that.salario) && Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(idade, that.idade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, salario, dataNascimento, idade);
    }

    @Override
    public String toString() {
        return "{" +
                "\n  nome: " + nome +
                "\n  salario: " + salario +
                "\n  dataNascimento: " + dataNascimento +
                "\n  idade: " + idade +
                "\n}";
    }
}
