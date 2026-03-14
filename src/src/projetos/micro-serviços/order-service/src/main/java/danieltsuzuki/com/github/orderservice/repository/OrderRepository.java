package danieltsuzuki.com.github.orderservice.repository;

import danieltsuzuki.com.github.orderservice.entity.Order;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<@NonNull  Order, @NonNull Long> {
    Page<?> findByCustomerId(Long customerId, Pageable pageable);
}