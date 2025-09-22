package projetos.PotalDeRecursosHumanos.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class PontoEntradaSaida {
    private LocalDate data;
    private LocalTime entrada;
    private LocalTime saida;

    public PontoEntradaSaida(LocalDate data, LocalTime entrada, LocalTime saida) {
        this.data = data;
        this.entrada = entrada;
        this.saida = saida;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public LocalTime getSaida() {
        return saida;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PontoEntradaSaida that = (PontoEntradaSaida) o;
        return Objects.equals(data, that.data) && Objects.equals(entrada, that.entrada) && Objects.equals(saida, that.saida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, entrada, saida);
    }

    @Override
    public String toString() {
        return "{" +
                "\n   data: " + data +
                "\n   entrada: " + entrada +
                "\n   saida: " + saida +
                "\n}";
    }
}
