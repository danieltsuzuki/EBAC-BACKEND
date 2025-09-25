package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Sistema {
    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>(Arrays.asList("Ana", "Bruno", "Carlos", "Amanda", "Beatriz"));
        Predicate<String> filtrarPorInicioComLetraA = (palavra) -> ((String) palavra).toLowerCase().startsWith("a");

        FiltroNome<String> filtrarNome = nome -> filtrarPorInicioComLetraA.test(nome);

        nomes.stream().filter(n -> filtrarNome.testar(n)).forEach(System.out::println);
    }



}
