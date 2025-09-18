package heranca.models;

import heranca.utils.GeradorDeProdutos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teste {
        static Map<Produto, Integer> carrinho = new HashMap<>();
        static List<Produto> produtos = GeradorDeProdutos.gerarProdutos();
    public static void main(String[] args) {

        carrinho.put(produtos.get(0), 1);
        carrinho.put(produtos.get(1), 2);
        carrinho.put(produtos.get(0), 3);
        System.out.println(carrinho.get(produtos.get(0)));
//        somaQuantidadeSeJaHouverProdutoIgualNoCarrinho(produtos.get(0), 3);
//        for (Map.Entry<Produto, Integer> produto : carrinho.entrySet()) {
//            System.out.println(produto);
//        }

    }

    public static void somaQuantidadeSeJaHouverProdutoIgualNoCarrinho(Produto produto, int quantidade) {
        if (carrinho.get(produto) != null) {
            carrinho.put(produto, carrinho.get(produto) + quantidade);
        }
    }
}
