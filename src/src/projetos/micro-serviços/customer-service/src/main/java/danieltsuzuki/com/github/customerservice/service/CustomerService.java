package danieltsuzuki.com.github.customerservice.service;

import danieltsuzuki.com.github.customerservice.entity.Customer;
import danieltsuzuki.com.github.customerservice.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Page<?> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
