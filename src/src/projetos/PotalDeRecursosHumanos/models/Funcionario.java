package projetos.PotalDeRecursosHumanos.models;

import projetos.PotalDeRecursosHumanos.exceptions.HoraExtraException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Funcionario {
    private UUID id;
    private String nome;
    private List<PontoEntradaSaida> pontosEntradaSaida;
    private TipoFuncionario tipoFuncionario;

    public Funcionario(String nome, TipoFuncionario tipoFuncionario) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.tipoFuncionario = tipoFuncionario;
        this.pontosEntradaSaida = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<PontoEntradaSaida> getPontosEntradaSaida() {
        return pontosEntradaSaida;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void adicionarPonto(PontoEntradaSaida pontoEntradaSaida) {
        validarDados(pontoEntradaSaida, this.tipoFuncionario);
        this.pontosEntradaSaida.add(pontoEntradaSaida);
    }

    private void validarDados(PontoEntradaSaida pontoEntradaSaida, TipoFuncionario tipoFuncionario) {
        long horaExtra = Duration.between(pontoEntradaSaida.getEntrada(), pontoEntradaSaida.getSaida()).toHours();
        if (tipoFuncionario.getMaximoHoraExtra() > horaExtra) {
            throw new HoraExtraException("Este funcionário não pode cumprir mais que " + tipoFuncionario.getMaximoHoraExtra() + " horas extra por dia");
        }
        if (pontoEntradaSaida.getEntrada().getHour() < 6) {
            throw new HoraExtraException("Não é permitido entrar antes das 06:00");
        }
        if (pontoEntradaSaida.getSaida().getHour() > 22) {
            throw new HoraExtraException("Não é permitido sair depois das 22:00");
        }
        if (pontoEntradaSaida.getSaida().getHour() < pontoEntradaSaida.getEntrada().getHour()) {
            throw new HoraExtraException("Não é permitido registrar o horário de saida anterior ao horário de entrada");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(pontosEntradaSaida, that.pontosEntradaSaida) && tipoFuncionario == that.tipoFuncionario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, pontosEntradaSaida, tipoFuncionario);
    }

    @Override
    public String toString() {
        return "{" +
                "\n   id: " + id +
                "\n   nome: " + nome +
                "\n   tipoFuncionario: " + tipoFuncionario.getTipoFuncionario() +
                "\n}";
    }
}
