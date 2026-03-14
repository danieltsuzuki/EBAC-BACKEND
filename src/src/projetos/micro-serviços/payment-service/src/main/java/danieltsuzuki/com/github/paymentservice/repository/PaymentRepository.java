package danieltsuzuki.com.github.paymentservice.repository;

import danieltsuzuki.com.github.paymentservice.entity.Payment;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<@NonNull Payment, @NonNull Long> {
    Optional<Payment> findByOrderId(Long orderId);
}
