package streamApi1;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Seed {

    public static List<Empresa> criarEmpresas() {
        List<Empresa> empresas = new ArrayList<>();

        // Empresa 1: TechCorp - 20 funcionários
        List<Funcionario> funcionariosTechCorp = Arrays.asList(
                new Funcionario("Ana Silva", new BigDecimal("2500.00"), LocalDate.of(1980, 3, 15)),
                new Funcionario("Carlos Santos", new BigDecimal("4500.00"), LocalDate.of(1978, 7, 22)),
                new Funcionario("Maria Oliveira", new BigDecimal("2800.00"), LocalDate.of(1982, 11, 8)),
                new Funcionario("João Pereira", new BigDecimal("3200.00"), LocalDate.of(1975, 5, 30)),
                new Funcionario("Fernanda Costa", new BigDecimal("2200.00"), LocalDate.of(1983, 9, 12)),
                new Funcionario("Roberto Lima", new BigDecimal("5500.00"), LocalDate.of(1970, 1, 18)),
                new Funcionario("Juliana Rocha", new BigDecimal("2900.00"), LocalDate.of(1981, 4, 25)),
                new Funcionario("Pedro Alves", new BigDecimal("3800.00"), LocalDate.of(1977, 12, 3)),
                new Funcionario("Camila Ferreira", new BigDecimal("2600.00"), LocalDate.of(1984, 6, 14)),
                new Funcionario("Lucas Martins", new BigDecimal("3100.00"), LocalDate.of(1979, 10, 7)),
                new Funcionario("Beatriz Souza", new BigDecimal("2400.00"), LocalDate.of(1982, 2, 28)),
                new Funcionario("Rafael Barbosa", new BigDecimal("4200.00"), LocalDate.of(1976, 8, 16)),
                new Funcionario("Larissa Gomes", new BigDecimal("2700.00"), LocalDate.of(1983, 12, 21)),
                new Funcionario("Thiago Ribeiro", new BigDecimal("3600.00"), LocalDate.of(1978, 3, 9)),
                new Funcionario("Patrícia Dias", new BigDecimal("2300.00"), LocalDate.of(1984, 7, 4)),
                new Funcionario("Marcos Cardoso", new BigDecimal("4800.00"), LocalDate.of(1974, 11, 13)),
                new Funcionario("Gabriela Mendes", new BigDecimal("2950.00"), LocalDate.of(1981, 5, 26)),
                new Funcionario("Diego Nascimento", new BigDecimal("3400.00"), LocalDate.of(1979, 9, 17)),
                new Funcionario("Vanessa Torres", new BigDecimal("2650.00"), LocalDate.of(1982, 1, 11)),
                new Funcionario("André Campos", new BigDecimal("3900.00"), LocalDate.of(1977, 6, 23))
        );

        // Empresa 2: InnovaSoft - 18 funcionários
        List<Funcionario> funcionariosInnovaSoft = Arrays.asList(
                new Funcionario("Renata Moreira", new BigDecimal("2750.00"), LocalDate.of(1980, 8, 19)),
                new Funcionario("Felipe Araújo", new BigDecimal("4100.00"), LocalDate.of(1976, 4, 12)),
                new Funcionario("Cristina Lopes", new BigDecimal("2450.00"), LocalDate.of(1983, 10, 5)),
                new Funcionario("Gustavo Freitas", new BigDecimal("3300.00"), LocalDate.of(1978, 12, 28)),
                new Funcionario("Isabela Castro", new BigDecimal("2850.00"), LocalDate.of(1981, 2, 14)),
                new Funcionario("Rodrigo Pinto", new BigDecimal("5200.00"), LocalDate.of(1973, 6, 7)),
                new Funcionario("Amanda Vieira", new BigDecimal("2550.00"), LocalDate.of(1984, 3, 31)),
                new Funcionario("Bruno Correia", new BigDecimal("3750.00"), LocalDate.of(1977, 9, 24)),
                new Funcionario("Carla Ramos", new BigDecimal("2350.00"), LocalDate.of(1982, 11, 16)),
                new Funcionario("Eduardo Teixeira", new BigDecimal("4300.00"), LocalDate.of(1975, 7, 8)),
                new Funcionario("Priscila Nunes", new BigDecimal("2900.00"), LocalDate.of(1980, 1, 22)),
                new Funcionario("Leandro Silva", new BigDecimal("3500.00"), LocalDate.of(1979, 5, 15)),
                new Funcionario("Tatiana Moura", new BigDecimal("2650.00"), LocalDate.of(1983, 8, 3)),
                new Funcionario("Fábio Cunha", new BigDecimal("4000.00"), LocalDate.of(1976, 12, 10)),
                new Funcionario("Natália Reis", new BigDecimal("2800.00"), LocalDate.of(1981, 4, 27)),
                new Funcionario("Vinicius Monteiro", new BigDecimal("3200.00"), LocalDate.of(1978, 10, 18)),
                new Funcionario("Simone Batista", new BigDecimal("2500.00"), LocalDate.of(1984, 6, 9)),
                new Funcionario("Henrique Fonseca", new BigDecimal("4600.00"), LocalDate.of(1974, 2, 1))
        );

        // Empresa 3: DataSolutions - 16 funcionários
        List<Funcionario> funcionariosDataSolutions = Arrays.asList(
                new Funcionario("Aline Machado", new BigDecimal("2600.00"), LocalDate.of(1982, 5, 13)),
                new Funcionario("Márcio Azevedo", new BigDecimal("3900.00"), LocalDate.of(1977, 11, 6)),
                new Funcionario("Luciana Borges", new BigDecimal("2400.00"), LocalDate.of(1983, 7, 29)),
                new Funcionario("Daniel Carvalho", new BigDecimal("3100.00"), LocalDate.of(1979, 3, 21)),
                new Funcionario("Mônica Duarte", new BigDecimal("2750.00"), LocalDate.of(1981, 9, 14)),
                new Funcionario("Alexandre Melo", new BigDecimal("4700.00"), LocalDate.of(1975, 1, 25)),
                new Funcionario("Eliane Santana", new BigDecimal("2550.00"), LocalDate.of(1984, 4, 17)),
                new Funcionario("Sérgio Nogueira", new BigDecimal("3600.00"), LocalDate.of(1978, 8, 11)),
                new Funcionario("Karina Paiva", new BigDecimal("2300.00"), LocalDate.of(1982, 12, 4)),
                new Funcionario("Otávio Guimarães", new BigDecimal("4400.00"), LocalDate.of(1976, 6, 26)),
                new Funcionario("Denise Cavalcanti", new BigDecimal("2850.00"), LocalDate.of(1980, 10, 19)),
                new Funcionario("Renato Brandão", new BigDecimal("3300.00"), LocalDate.of(1979, 2, 12)),
                new Funcionario("Cláudia Medeiros", new BigDecimal("2700.00"), LocalDate.of(1983, 5, 8)),
                new Funcionario("Júlio César", new BigDecimal("3800.00"), LocalDate.of(1977, 9, 30)),
                new Funcionario("Adriana Coelho", new BigDecimal("2450.00"), LocalDate.of(1984, 1, 23)),
                new Funcionario("Wagner Tavares", new BigDecimal("4900.00"), LocalDate.of(1973, 7, 16))
        );

        empresas.add(new Empresa(funcionariosTechCorp, "TechCorp"));
        empresas.add(new Empresa(funcionariosInnovaSoft, "InnovaSoft"));
        empresas.add(new Empresa(funcionariosDataSolutions, "DataSolutions"));

        return empresas;
    }
}
