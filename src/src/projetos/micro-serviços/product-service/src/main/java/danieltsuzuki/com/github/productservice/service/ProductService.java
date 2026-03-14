package danieltsuzuki.com.github.productservice.service;

import danieltsuzuki.com.github.productservice.entity.Product;
import danieltsuzuki.com.github.productservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Page<?> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    @Transactional
    public boolean reduceStock(Long productId, int quantity) {
        return repository.findById(productId).map(product -> {
            if (product.getStockQuantity() >= quantity) {
                product.setStockQuantity(product.getStockQuantity() - quantity);
                repository.save(product);
                return true;
            }
            return false;
        }).orElse(false);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
