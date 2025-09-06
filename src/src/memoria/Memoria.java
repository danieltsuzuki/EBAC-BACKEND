package memoria;

import java.util.HashSet;
import java.util.Set;

public class Memoria {
    public static void main(String[] args) {
        Pessoa pessoa1 = new Pessoa("Alice", 30);
        Pessoa pessoa2 = new Pessoa("Bob", 25);
        Pessoa pessoa3 = new Pessoa("Alice", 30);
        Pessoa pessoa4 = new Pessoa("Alice", 30);

        Set<Pessoa> pessoas = new HashSet<>();
        pessoas.add(pessoa1);
        pessoas.add(pessoa2);
        pessoas.add(pessoa3);
        pessoas.add(pessoa4);

        System.out.println("Quantidade de pessoas: " + pessoas.size() + "\n " + pessoas);

        System.out.println("Foi impresso apenas Alice e Bob, pois na classe Pessoa os métodos hashCode e equals foram " +
                "sobrescritos para considerar dois objetos iguais se tiverem o mesmo nome e idade.");
    }
}
