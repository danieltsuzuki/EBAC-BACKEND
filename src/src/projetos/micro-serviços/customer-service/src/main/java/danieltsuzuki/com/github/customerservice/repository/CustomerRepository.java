package danieltsuzuki.com.github.customerservice.repository;

import danieltsuzuki.com.github.customerservice.entity.Customer;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<@NonNull Customer, @NonNull Long> {
}
