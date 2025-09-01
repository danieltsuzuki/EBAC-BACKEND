package projetos.SistemaDeControleDeReservasDeHotel;

public class DAO {
    public Reserva[] reservas = new Reserva[10];
    
    public void listar() {
        System.out.println("---------------------");
        if (reservas[0] == null) {
            System.out.println("Nenhuma reserva cadastrada");
            System.out.println("---------------------");
            return;
        }
        for (Reserva reserva : reservas) {
            if (reserva != null) {
                System.out.println(reserva);
            }
        }
        System.out.println("---------------------");
    }

    public void buscar(String nomeHospede) {
        validaNome(nomeHospede);
        validaReservas();
        for (Reserva reserva : reservas) {
            if (reserva.getNomeHospede().equalsIgnoreCase(nomeHospede)) {
                System.out.println(reserva);
                return;
            }
        }
    }

    public void ordenarPorNumeroDias() {
        for(int i = 0; i < reservas.length; i++) {
            for(int j = i; j < reservas.length; j++) {
                if(reservas[i] != null && reservas[j] != null && reservas[i].getNumeroDias() > reservas[j].getNumeroDias()) {
                    Reserva temp = reservas[i];
                    reservas[i] = reservas[j];
                    reservas[j] = temp;
                }
            }
        }
        listar();
    }

    public void cadastrar(String nomeHospede, String tipoQuarto, int numeroDias, double valorDiaria) {
        validaReservas();
        validaDados(nomeHospede, tipoQuarto, numeroDias, valorDiaria);
        Reserva reserva = new Reserva(nomeHospede, tipoQuarto, numeroDias, valorDiaria);
        inserirReserva(reserva);
    }

    private void validaDados(String nomeHospede, String tipoQuarto, int numeroDias, double valorDiaria) {
        validaNome(nomeHospede);
        if (tipoQuarto == null || tipoQuarto.isEmpty()) {
            throw new IllegalArgumentException("Tipo de quarto inválido");
        }
        if (numeroDias <= 0) {
            throw new IllegalArgumentException("Número de dias inválido");
        }
        if (valorDiaria <= 0) {
            throw new IllegalArgumentException("Valor da diária inválido");
        }
    }

    private void validaReservas() {
        if (reservas[reservas.length -1] != null) {
            throw new IllegalArgumentException("Limite de reservas atingido");
        }
    }

    private void validaNome(String nomeHospede) {
        if (nomeHospede == null || nomeHospede.isEmpty()) {
            throw new IllegalArgumentException("Nome do hóspede inválido");
        }
    }

    private void inserirReserva(Reserva reserva) {
        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] == null) {
                reservas[i] = reserva;
                return;
            }
        }
    }
}
