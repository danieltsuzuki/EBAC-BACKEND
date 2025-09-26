package streamApi1;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    public static List<Funcionario> filtrarFuncionarios(List<Empresa> empresas) {
        return empresas.stream().map(funcionarios -> funcionarios.getFuncionarios().stream()
                .filter(f -> f.getDataNascimento().getYear() > 1975 &&
                        f.getDataNascimento().getYear() < 1985 &&
                        f.getSalario().compareTo(new BigDecimal(3000)) <= 0)
                .collect(Collectors.toList())).flatMap(List::stream).collect(Collectors.toList());

    }

    public static Integer somaValores(List<Integer> valores) {
        return valores.stream().reduce(0, Integer::sum);
    }

    public static List<BigDecimal> filtrar30MaioresSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(Funcionario::getSalario)
                .sorted(Comparator.reverseOrder()).limit(30).collect(Collectors.toList());
    }
}
