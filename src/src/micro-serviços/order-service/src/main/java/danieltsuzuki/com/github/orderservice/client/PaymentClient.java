package danieltsuzuki.com.github.orderservice.client;

import danieltsuzuki.com.github.orderservice.dto.PaymentDTO;
import danieltsuzuki.com.github.orderservice.dto.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PaymentClient {

    private static final Logger log = LoggerFactory.getLogger(PaymentClient.class);

    private final RestClient restClient;
    private final String paymentServiceUrl;

    public PaymentClient(RestClient restClient,
                         @Value("${services.payment.url}") String paymentServiceUrl) {
        this.restClient = restClient;
        this.paymentServiceUrl = paymentServiceUrl;
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "processPaymentFallback")
    public PaymentDTO processPayment(PaymentRequest request) {
        log.info("Chamando Payment Service para processar pagamento. Order: {}, Valor: {}",
                request.getOrderId(), request.getAmount());
        String url = paymentServiceUrl + "/api/payments/process";
        return restClient.post().uri(url).body(request).retrieve().body(PaymentDTO.class);
    }

    // FALLBACK para processamento de pagamento
    public PaymentDTO processPaymentFallback(PaymentRequest request, Throwable throwable) {
        log.warn("Circuit Breaker ativado para Payment Service. Erro: {}. " +
                        "Pagamento ficará pendente para order: {}",
                throwable.getMessage(), request.getOrderId());
        PaymentDTO fallback = new PaymentDTO();
        fallback.setOrderId(request.getOrderId());
        fallback.setAmount(request.getAmount());
        fallback.setStatus("PENDING_RETRY");
        fallback.setMethod(request.getMethod());
        return fallback;
    }
}
