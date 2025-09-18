package heranca;

import heranca.exceptions.ProdutoNaoEncontradoException;
import heranca.models.Produto;
import heranca.utils.GeradorDeProdutos;

import java.math.BigDecimal;
import java.util.*;

public class Sistema {
    static Map<Produto, Integer> carrinho = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static String opcao = "";
    static String codigoProduto = "";
    static int quantidade = 0;
    static Produto produto = null;

    public static void main(String[] args) {

        System.out.println("Seja bem vindo a loja EBAC");
        try {
            sistema();
        } catch (InputMismatchException e) {
            System.out.println("\n Quantidade inválida! \n");
            sistema();
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println("\n" + e.getMessage() + "\n");
            sistema();
        }
    }

    public static void sistema() {
        do {
            menu();
        } while(!opcao.equals("0"));
    }

    public static void listarProdutos(List<Produto> produtos) {
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public static void selecionarOpcao(String opcao) {
        switch (opcao) {
            case "1":
                listarProdutos(GeradorDeProdutos.gerarProdutos());
                break;
            case "2":
                System.out.println("Digite o codigo do produto: ");
                codigoProduto = sc.next();
                System.out.println("Digite o quantidade do produto: ");
                quantidade = sc.nextInt();
                produto = selecionaProduto(codigoProduto);
                adicionaProdutoNoCarrinho(produto, quantidade);
                break;
            case "3":
                System.out.println("Digite o codigo do produto: ");
                codigoProduto = sc.next();
                System.out.println("Digite o quantidade do produto: ");
                quantidade = sc.nextInt();
                produto = selecionaProduto(codigoProduto);
                removeProdutoDoCarrinho(produto, quantidade);
                break;
            case "4":
                verCarrinho();
                break;
            case "5":
                finalizarCompra();
                break;
            case "0":
                System.out.println("Programa finalizado com sucesso!");
                break;
            default:
                System.out.println("Selecione uma opção válida!");
                break;
        }
    }

    public static Produto selecionaProduto(String codigoProduto) {
        return GeradorDeProdutos.gerarProdutos().stream().filter(produto -> produto.getCodigo().equals(codigoProduto)).findFirst().orElse(null);
    }

    public static void adicionaProdutoNoCarrinho(Produto produto, int quantidade) {
        if (existeProdutoNoCarrinho(produto)) {
            carrinho.put(produto, carrinho.get(produto) + quantidade);
            return;
        }
        carrinho.put(produto, quantidade);
    }

    public static boolean existeProdutoNoCarrinho(Produto produto) {
        return carrinho.get(produto) != null;
    }

    public static void removeProdutoDoCarrinho(Produto produto, int quantidade) {
        validaRemocao(produto, quantidade);
        if (existeProdutoNoCarrinho(produto)) {
            validaRemocao(produto, quantidade);
            carrinho.put(produto, carrinho.get(produto) - quantidade);
            return;
        }
        carrinho.remove(produto);
    }

    public static void validaRemocao(Produto produto, int quantidade) {
        if (produto == null) {
            throw new ProdutoNaoEncontradoException("Erro: Produto não encontrado");
        }
        int quantidadeNoCarrinho = carrinho.get(produto);
        if (quantidadeNoCarrinho < quantidade) {
            throw new ProdutoNaoEncontradoException("Erro: você está tentando remover mais do que há no carrinho");
        }
    }

    public static void menu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1 - Listar produtos");
        System.out.println("2 - Adicionar produto ao carrinho");
        System.out.println("3 - Remover produto ao carrinho");
        System.out.println("4 - Ver carrinho");
        System.out.println("5 - finalizar compra");
        System.out.println("0 - Sair");
        opcao = sc.next();
        selecionarOpcao(opcao);
    }

    public static void finalizarCompra() {
        for (Produto produto : carrinho.keySet()) {
            System.out.println(produto.getNome() + " - " + carrinho.get(produto) + " UN - Valor: R$" + calculaTotalDoProduto(produto) );
        }
        System.out.println("------------------");
        System.out.println("Total da compra: R$" + calculaTotalDoCarrinho());
        opcao = "0";
    }

    public static BigDecimal calculaTotalDoProduto(Produto produto) {
        return produto.getPreco().multiply(new BigDecimal(carrinho.get(produto)));
    }

    public static BigDecimal calculaTotalDoCarrinho() {
        BigDecimal total = BigDecimal.ZERO;
        for (Produto produto : carrinho.keySet()) {
            total = total.add(calculaTotalDoProduto(produto));
        }
        return total;
    }

    public static void verCarrinho() {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio! \n\n");
            return;
        }
        System.out.println("Carrinho: ");
        for (Produto produto : carrinho.keySet()) {
            System.out.println(produto.getNome() + " - " + carrinho.get(produto) + " UN - Valor: R$" + calculaTotalDoProduto(produto));
        }
        System.out.println();
    }

}
