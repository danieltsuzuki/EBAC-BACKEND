//package danieltsuzuki.com.github.productservice.config;
//
//import danieltsuzuki.com.github.productservice.entity.Product;
//import danieltsuzuki.com.github.productservice.repository.ProductRepository;
//import org.jspecify.annotations.NullMarked;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//    private final ProductRepository repository;
//
//    public DataLoader(ProductRepository repository) {
//        this.repository = repository;
//    }
//
//    @NullMarked
//    @Override
//    public void run(String... args) {
//        repository.save(new Product("Notebook Dell", "Notebook Dell Inspiron 15",
//                new BigDecimal("4500.00"), 50, "Eletrônicos"));
//        repository.save(new Product("Mouse Logitech", "Mouse sem fio Logitech MX",
//                new BigDecimal("350.00"), 200, "Periféricos"));
//        repository.save(new Product("Teclado Mecânico", "Teclado mecânico RGB",
//                new BigDecimal("280.00"), 150, "Periféricos"));
//    }
//}
