package streamApi2;

import java.util.ArrayList;
import java.util.List;

public class Seed {
    public static List<Produto> criarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("Notebook Dell XPS 13", 7999.90));
        produtos.add(new Produto("Smartphone Galaxy S23", 4299.00));
        produtos.add(new Produto("Smart TV LG OLED 55\"", 5299.99));
        produtos.add(new Produto("Fone Bluetooth", 99.90));
        produtos.add(new Produto("Console PlayStation 5", 4499.00));
        produtos.add(new Produto("Cafeteira Expresso Philips", 599.90));
        produtos.add(new Produto("Monitor Gamer 27\" 144Hz", 1599.00));
        produtos.add(new Produto("Teclado Mecânico Redragon", 349.90));
        produtos.add(new Produto("Mouse Logitech", 49.90));
        produtos.add(new Produto("Geladeira Frost Free Brastemp", 3899.00));
        produtos.add(new Produto("Máquina de Lavar Samsung", 3199.90));
        produtos.add(new Produto("Ventilador Arno Turbo", 229.00));
        produtos.add(new Produto("Aspirador de Pó Electrolux", 499.00));
        produtos.add(new Produto("Tablet iPad 10ª Geração", 3599.00));
        produtos.add(new Produto("Cadeira Gamer DT3", 1299.00));
        produtos.add(new Produto("Relógio Smartwatch Amazfit", 899.90));
        produtos.add(new Produto("Caixa de Som Bose", 1499.00));
        produtos.add(new Produto("Micro-ondas Panasonic", 699.00));
        produtos.add(new Produto("Cooktop 4 Bocas Brastemp", 1199.00));
        produtos.add(new Produto("Impressora Multifuncional HP", 899.00));

        return produtos;
    }
}
