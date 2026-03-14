package danieltsuzuki.com.github.paymentservice.service;

import danieltsuzuki.com.github.paymentservice.dto.PaymentRequest;
import danieltsuzuki.com.github.paymentservice.entity.Payment;
import danieltsuzuki.com.github.paymentservice.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment processPayment(PaymentRequest request) {
        Payment payment = new Payment(
                request.getOrderId(),
                request.getAmount(),
                Payment.PaymentMethod.valueOf(request.getMethod())
        );

        payment.setStatus(Payment.PaymentStatus.APPROVED);
        payment.setProcessedAt(LocalDateTime.now());

        return repository.save(payment);
    }

    public Page<?> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Payment> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Payment> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }
}
