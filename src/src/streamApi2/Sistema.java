package streamApi2;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Sistema {
    public static void main(String[] args) {
        List<Produto> produtos = Seed.criarProdutos();
        List<Produto> produtosMaior100 = produtos.stream().filter(produto -> produto.getPreco() > 100).toList();
        System.out.println("Produtos com valores acima de 100: " + produtosMaior100);
        List<Produto> produtosOrdenadosPorNome = produtos.stream().sorted(Comparator.comparing(Produto::getNome)).toList();
        System.out.println("Produtos ordenados por nome: " + produtosOrdenadosPorNome);
        Optional<Produto> produtoPesquisado = produtos.stream().filter(p -> p.getNome().equals("Sansaung")).findFirst();
        produtoPesquisado.ifPresent(System.out::println);
        produtoPesquisado.ifPresentOrElse(System.out::println, () -> {
            System.out.println("\nProduto não encontrado");
        });
    }
}
