package projetos.SistemaDeControleDeReservasDeHotel;

public class Reserva {
    private String nomeHospede;
    private String tipoQuarto;
    private int numeroDias;
    private double valorDiaria;

    public Reserva(String nomeHospede, String tipoQuarto) {
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;
    }

    public Reserva(String nomeHospede, String tipoQuarto, int numeroDias, double valorDiaria) {
        try {
            validaDados(numeroDias, valorDiaria);
            this.nomeHospede = nomeHospede;
            this.tipoQuarto = tipoQuarto;
            this.numeroDias = numeroDias;
            this.valorDiaria = valorDiaria;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    @Override
    public String toString() {
        return "nome='" + nomeHospede + '\'' +
                ", tipoQuarto='" + tipoQuarto + '\'' +
                ", numeroDias=" + numeroDias +
                ", valorDiaria=" + valorDiaria +
                ", valorTotal=" + String.format("%.2f", calcularValorTotal());
    }

    public double calcularValorTotal() {
        return numeroDias * valorDiaria;
    }

    private void validaDados(int numeroDias, double valorDiaria) {
        if (numeroDias <= 0) {
            throw new IllegalArgumentException("Número de dias deve ser maior que zero.");
        }
        if (valorDiaria < 0) {
            throw new IllegalArgumentException("Valor da diária não pode ser negativo.");
        }
    }
}
