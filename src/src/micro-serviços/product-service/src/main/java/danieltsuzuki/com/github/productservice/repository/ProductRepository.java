package danieltsuzuki.com.github.productservice.repository;

import danieltsuzuki.com.github.productservice.entity.Product;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<@NonNull Product, @NonNull Long> {
}
