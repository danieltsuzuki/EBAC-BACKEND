package projetos.SistemaDeControleDeReservasDeHotel;

import java.util.Scanner;

public class Sistema {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            DAO dao = new DAO();
            int opcao = 0;
            do {
                opcao = escolherOpcao();
                switch (opcao) {
                    case 1:
                        preencherDados(dao);
                        break;
                    case 2:
                        dao.listar();
                        break;
                    case 3:
                        buscarPorNome(dao);
                        break;
                    case 4:
                        dao.ordenarPorNumeroDias();
                        break;
                    case 5:
                        opcao = 0;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } while (opcao != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static int escolherOpcao() {
        System.out.println("Digite: 1 - Cadastrar reserva");
        System.out.println("Digite: 2 - Listar reservas");
        System.out.println("Digite: 3 - Buscar reserva por nome do hospede");
        System.out.println("Digite: 4 - Ordenar reservas por número de dias");
        System.out.println("Digite: 5 - Sair");
        return scanner.nextInt();
    }

    public static void preencherDados(DAO dao) {
        System.out.println("Digite: nome do hospede");
        String nomeHospede = scanner.next();
        System.out.println("Digite: tipo do quarto");
        String tipoQuarto = scanner.next();
        System.out.println("Digite: numero de dias");
        int numeroDias = scanner.nextInt();
        System.out.println("Digite: valor da diaria");
        double valorDiaria = scanner.nextDouble();
        dao.cadastrar(nomeHospede, tipoQuarto, numeroDias, valorDiaria);
    }

    public static void buscarPorNome(DAO dao) {
        System.out.println("Digite: nome do hospede");
        String nomeHospede = scanner.next();
        dao.buscar(nomeHospede);
    }

}
