package heranca.utils;

import heranca.models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GeradorDeProdutos {

    public static List<Produto> gerarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        // Celulares
        produtos.add(new Celular("CEL001", "iPhone 15", "Smartphone Apple com câmera avançada", new BigDecimal("4999.00"), "Apple", 0.0f, 6.1f, 3349));
        produtos.add(new Celular("CEL002", "Galaxy S24", "Smartphone Samsung com IA integrada", new BigDecimal("3799.00"), "Samsung", 0.0f, 6.2f, 4000));
        produtos.add(new Celular("CEL003", "Xiaomi Redmi Note 13", "Smartphone com ótimo custo-benefício", new BigDecimal("1299.00"), "Xiaomi", 1.0f, 6.67f, 5000));

        // Tablets
        produtos.add(new Tablet("TAB001", "iPad Air", "Tablet Apple para produtividade", new BigDecimal("3499.00"), "Apple", false, 10.9f));
        produtos.add(new Tablet("TAB002", "Galaxy Tab S9", "Tablet Samsung com S Pen", new BigDecimal("2899.00"), "Samsung", true, 11.0f));
        produtos.add(new Tablet("TAB003", "Fire HD 10", "Tablet Amazon para entretenimento", new BigDecimal("699.00"), "Amazon", false, 10.1f));

        // Televisões
        produtos.add(new Televisao("TV001", "Smart TV OLED 55\"", "TV OLED 4K com HDR", new BigDecimal("4299.00"), "LG", 55.0f, true));
        produtos.add(new Televisao("TV002", "Neo QLED 65\"", "TV Samsung com tecnologia Quantum Dot", new BigDecimal("6999.00"), "Samsung", 65.0f, true));
        produtos.add(new Televisao("TV003", "Smart TV LED 32\"", "TV básica para quarto", new BigDecimal("1199.00"), "TCL", 32.0f, false));

        // Videogames
        produtos.add(new Videogame("VG001", "PlayStation 5", "Console Sony de nova geração", new BigDecimal("3999.00"), "Sony", true, 1));
        produtos.add(new Videogame("VG002", "Xbox Series X", "Console Microsoft 4K", new BigDecimal("4199.00"), "Microsoft", true, 1));
        produtos.add(new Videogame("VG003", "Nintendo Switch", "Console portátil Nintendo", new BigDecimal("2299.00"), "Nintendo", false, 2));

        // Rádios
        produtos.add(new Radio("RAD001", "Rádio Portátil Bluetooth", "Rádio com conectividade moderna", new BigDecimal("299.00"), "Philips", 2, 0, false));
        produtos.add(new Radio("RAD002", "Som Automotivo", "Rádio para carro com CD", new BigDecimal("459.00"), "Pioneer", 1, 1, false));
        produtos.add(new Radio("RAD003", "Mini System", "Sistema de som completo", new BigDecimal("899.00"), "Sony", 3, 1, true));

        return produtos;
    }

}
