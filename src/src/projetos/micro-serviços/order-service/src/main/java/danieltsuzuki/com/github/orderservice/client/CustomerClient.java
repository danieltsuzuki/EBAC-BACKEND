package danieltsuzuki.com.github.orderservice.client;

import danieltsuzuki.com.github.orderservice.dto.CustomerDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerClient {

    private static final Logger log = LoggerFactory.getLogger(CustomerClient.class);

    private final RestClient restClient;
    private final String customerServiceUrl;

    public CustomerClient(RestClient restClient,
                          @Value("${services.customer.url}") String customerServiceUrl) {
        this.restClient = restClient;
        this.customerServiceUrl = customerServiceUrl;
    }

    @CircuitBreaker(name = "customerService", fallbackMethod = "getCustomerFallback")
    public CustomerDTO getCustomerById(Long id) {
        log.info("Chamando Customer Service para buscar cliente ID: {}", id);
        String url = customerServiceUrl + "/api/customers/" + id;
        return restClient.get().uri(url).retrieve().body(CustomerDTO.class);
    }

    // FALLBACK - retorna um cliente padrão quando o serviço está indisponível
    public CustomerDTO getCustomerFallback(Long id, Throwable throwable) {
        log.warn("Circuit Breaker ativado para Customer Service. Erro: {}. " +
                "Retornando fallback para cliente ID: {}", throwable.getMessage(), id);
        CustomerDTO fallback = new CustomerDTO();
        fallback.setId(id);
        fallback.setName("Cliente Indisponível (Fallback)");
        fallback.setEmail("fallback@email.com");
        fallback.setActive(true);
        return fallback;
    }
}
