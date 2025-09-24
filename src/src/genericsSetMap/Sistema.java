package genericsSetMap;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<Produto>();
        produtos.add(new Produto("Telefone", 3000.0));
        produtos.add(new Produto("Arroz", 40.0));
        produtos.add(new Produto("Mouse", 129.0));
        ListaUtil.ordenarExibir(produtos);
    }
}
