//package danieltsuzuki.com.github.customerservice.config;
//
//import danieltsuzuki.com.github.customerservice.entity.Customer;
//import danieltsuzuki.com.github.customerservice.repository.CustomerRepository;
//import org.jspecify.annotations.NullMarked;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//    private final CustomerRepository repository;
//
//    public DataLoader(CustomerRepository repository) {
//        this.repository = repository;
//    }
//
//    @NullMarked
//    @Override
//    public void run(String... args) {
//        repository.save(new Customer("João Silva", "joao@email.com", "11999990000", "Rua A, 100"));
//        repository.save(new Customer("Maria Santos", "maria@email.com", "11988880000", "Rua B, 200"));
//        repository.save(new Customer("Pedro Oliveira", "pedro@email.com", "11977770000", "Rua C, 300"));
//    }
//}
