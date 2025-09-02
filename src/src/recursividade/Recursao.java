package recursividade;

public class Recursao {
    public Recursao(int tamanhoEscadaEmDegraus) {
        System.out.println(subirDegrau(tamanhoEscadaEmDegraus));
    }

    public int subirDegrau(int tamanhoEscadaEmDegraus) {
        if (tamanhoEscadaEmDegraus <= 1) {
            return 1;
        }
        return subirDegrau(tamanhoEscadaEmDegraus - 1) + subirDegrau(tamanhoEscadaEmDegraus - 2);
    }
}
