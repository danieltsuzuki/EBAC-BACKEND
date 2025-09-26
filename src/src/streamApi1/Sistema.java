package streamApi1;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sistema {
    public static void main(String[] args) {
        List<Empresa> empresas = Seed.criarEmpresas();
        System.out.println("Funcionários com salarios menor ou igual a 3000: " + Util.filtrarFuncionarios(empresas));
        List<Integer> idades = empresas.stream()
                .map(funcionarios -> new ArrayList<>(funcionarios.getFuncionarios()))
                        .flatMap(List::stream).map(Funcionario::getIdade).toList();
        System.out.println("Somatório das idades: " + Util.somaValores(idades));
        List<Funcionario> funcionarios = empresas.stream().map(Empresa::getFuncionarios).flatMap(List::stream).toList();
        System.out.println("30 maiores salários: " + Util.filtrar30MaioresSalarios(funcionarios));

    }

}
