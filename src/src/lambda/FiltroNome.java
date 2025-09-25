package lambda;

@FunctionalInterface
public interface FiltroNome<String> {
    boolean testar(String nome);
}
