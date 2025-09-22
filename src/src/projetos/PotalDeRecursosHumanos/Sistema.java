package projetos.PotalDeRecursosHumanos;

import projetos.PotalDeRecursosHumanos.exceptions.FuncionarioNaoEncontradoException;
import projetos.PotalDeRecursosHumanos.exceptions.HoraExtraException;
import projetos.PotalDeRecursosHumanos.models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Sistema {
    public static List<Funcionario> funcionarios = new ArrayList<Funcionario>();
    public static Scanner scanner = new Scanner(System.in);
    public static String opcao = "";
    public static String nome = "";
    public static PontoEntradaSaida pontoEntradaSaida = null;
    public static Funcionario funcionario = null;
    public static String tipoFuncionario = "";
    public static String id = "";
    public static String dataString = "";
    public static String entradaString = "";
    public static String saidaString = "";
    public static LocalDate data = null;
    public static LocalTime entrada = null;
    public static LocalTime saida = null;
    public static DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatorDeHora = DateTimeFormatter.ofPattern("HH:mm");


    public static void main(String[] args) {
        System.out.println("Seja bem vindo ao postal de recursos humanos");
        do {
            try {
                opcoes();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } while (!Objects.equals(opcao, "0"));
    }

    public static void opcoes() {
        System.out.println("\n1 - Cadastrar funcionario");
        System.out.println("2 - Listar funcionarios");
        System.out.println("3 - Buscar funcionario");
        System.out.println("4 - Excluir funcionario");
        System.out.println("0 - Sair");
        opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                cadastrarFuncionario();
                funcionarios.add(funcionario);
                System.out.println(funcionario.toString());
                break;
            case "2":
                listarFuncionarios();
                break;
            case "3":
                buscarFuncionario();
                opcoesFuncionario();
                break;
            case "4":
                removerFuncionario();
                break;
            case "0":
                System.out.println("Programa finalizado");
                break;
            default:
                System.out.println("Opcao invalida");
                opcoes();
        }
    }

    public static void cadastrarFuncionario() {
        System.out.println("\n1 - Coordenador");
        System.out.println("2 - Analista");
        System.out.println("3 - Assistente");
        System.out.println("4 - Gerente");
        System.out.println("5 - Estagiário");

        System.out.println("Digite o tipo do funcionário:");
        tipoFuncionario = scanner.nextLine();

        System.out.println("Digite o nome do funcionario:");
        nome = scanner.nextLine();

        switch (tipoFuncionario) {
            case "1":
                funcionario = new Coordenador(nome);
                break;
            case "2":
                funcionario = new Analista(nome);
                break;
            case "3":
                funcionario = new Assistente(nome);
                break;
            case "4":
                funcionario = new Gerente(nome);
                break;
            case "5":
                funcionario = new Estagiario(nome);
                break;
            default:
                System.out.println("Opcao invalida");
                cadastrarFuncionario();
        }
    }

    public static void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado");
            return;
        }
        System.out.println("\nTotal: " + funcionarios.size());
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    public static Funcionario buscarFuncionario()  {
        System.out.println("Digite o id do funcionário:");
        id = scanner.nextLine();
        return funcionarios.stream().filter(f -> f.getId().equals(UUID.fromString(id))).findFirst()
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário com ID " + id + " não encontrado"));
    }

    public static void removerFuncionario()  {
        funcionario = buscarFuncionario();
        funcionarios.remove(funcionario);
    }

    public static void opcoesFuncionario() {
        while (!Objects.equals(opcao, "0")) {
            System.out.println("\n1 - Listar pontos");
            System.out.println("2 - Registrar ponto");
            System.out.println("0 - Voltar");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    listarPontosDoFuncionario();
                    break;
                case "2":
                    cadastrarPonto();
                    break;
                case "0":
                    opcoes();
                    break;
                default:
                    System.out.println("Opcao invalida");
                    opcoesFuncionario();
            }
        }
    }

    public static void listarPontosDoFuncionario() {
        if (funcionario.getPontosEntradaSaida().isEmpty()) {
            System.out.println("Nenhum ponto cadastrado");
            opcoesFuncionario();
            return;
        }
        for (PontoEntradaSaida ponto : funcionario.getPontosEntradaSaida()) {
            System.out.println(ponto.toString());
        }
    }

    public static void cadastrarPonto() {
        try {
            validaTipoFuncionario();
            System.out.println("\nDigite a data dd/MM/yyyy");
            dataString = scanner.nextLine();
            data = LocalDate.parse(dataString, formatadorDeData);

            System.out.println("Digite a hora de entrada hh:mm");
            entradaString = scanner.nextLine();
            entrada = LocalTime.parse(entradaString, formatorDeHora);

            System.out.println("Digite o saida hh:mm");
            saidaString = scanner.nextLine();
            saida = LocalTime.parse(saidaString, formatorDeHora);

            PontoEntradaSaida ponto = new PontoEntradaSaida(data, entrada, saida);
            funcionario.adicionarPonto(ponto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void validaTipoFuncionario() {
        if (funcionario.getTipoFuncionario() == TipoFuncionario.ESTAGIARIO ||
                funcionario.getTipoFuncionario() == TipoFuncionario.GERENTE) {
            throw new HoraExtraException("Funcionários do tipo (" +
                    funcionario.getTipoFuncionario().getTipoFuncionario() + ") não pode bater ponto");
        }
    }

}
