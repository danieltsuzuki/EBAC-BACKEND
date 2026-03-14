package danieltsuzuki.com.github.orderservice.client;

import danieltsuzuki.com.github.orderservice.dto.ProductDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Component
public class ProductClient {

    private static final Logger log = LoggerFactory.getLogger(ProductClient.class);

    private final RestClient restClient;
    private final String productServiceUrl;

    public ProductClient(RestClient restClient,
                         @Value("${services.product.url}") String productServiceUrl) {
        this.restClient = restClient;
        this.productServiceUrl = productServiceUrl;
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "getProductFallback")
    public ProductDTO getProductById(Long id) {
        log.info("Chamando Product Service para buscar produto ID: {}", id);
        String url = productServiceUrl + "/api/products/" + id;
        return restClient.get().uri(url).retrieve().body(ProductDTO.class);
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "reduceStockFallback")
    public Boolean reduceStock(Long productId, int quantity) {
        log.info("Chamando Product Service para reduzir estoque. Produto: {}, Qtd: {}",
                productId, quantity);
        String url = productServiceUrl + "/api/products/" + productId +
                "/reduce-stock?quantity=" + quantity;
        return restClient.patch().uri(url).retrieve().body(Boolean.class);
    }

    // FALLBACK para busca de produto
    public ProductDTO getProductFallback(Long id, Throwable throwable) {
        log.warn("Circuit Breaker ativado para Product Service. Erro: {}. " +
                "Retornando fallback para produto ID: {}", throwable.getMessage(), id);
        ProductDTO fallback = new ProductDTO();
        fallback.setId(id);
        fallback.setName("Produto Indisponível (Fallback)");
        fallback.setPrice(BigDecimal.ZERO);
        fallback.setStockQuantity(0);
        fallback.setActive(false);
        return fallback;
    }

    // FALLBACK para redução de estoque
    public Boolean reduceStockFallback(Long productId, int quantity, Throwable throwable) {
        log.warn("Circuit Breaker ativado para redução de estoque. Erro: {}. " +
                "Produto: {}", throwable.getMessage(), productId);
        return false;
    }
}