package danieltsuzuki.com.github.orderservice.service;

import danieltsuzuki.com.github.orderservice.client.CustomerClient;
import danieltsuzuki.com.github.orderservice.client.PaymentClient;
import danieltsuzuki.com.github.orderservice.client.ProductClient;
import danieltsuzuki.com.github.orderservice.dto.*;
import danieltsuzuki.com.github.orderservice.entity.Order;
import danieltsuzuki.com.github.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;

    public OrderService(OrderRepository repository,
                        CustomerClient customerClient,
                        ProductClient productClient,
                        PaymentClient paymentClient) {
        this.repository = repository;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.paymentClient = paymentClient;
    }

    /**
     * FLUXO COMPLETO: Customer -> Product -> Order -> Payment
     * Este fluxo toca TODOS os 4 microsserviços.
     */
    @Transactional
    public Order createOrder(OrderRequest request) {
        log.info("=== INICIANDO FLUXO DE CRIAÇÃO DE PEDIDO ===");

        // 1. Validar Cliente (Customer Service)
        log.info("Passo 1: Validando cliente ID: {}", request.getCustomerId());
        CustomerDTO customer = customerClient.getCustomerById(request.getCustomerId());

        if (customer == null || !customer.isActive()) {
            throw new RuntimeException("Cliente não encontrado ou inativo: " +
                    request.getCustomerId());
        }
        log.info("Cliente validado: {}", customer.getName());

        // 2. Verificar Produto e Estoque (Product Service)
        log.info("Passo 2: Verificando produto ID: {}", request.getProductId());
        ProductDTO product = productClient.getProductById(request.getProductId());

        if (product == null || !product.isActive()) {
            throw new RuntimeException("Produto não encontrado ou inativo: " +
                    request.getProductId());
        }

        if (product.getStockQuantity() < request.getQuantity()) {
            throw new RuntimeException("Estoque insuficiente para o produto: " +
                    product.getName());
        }
        log.info("Produto validado: {} - Preço: {}", product.getName(), product.getPrice());

        // 3. Calcular total e criar pedido (Order Service - próprio domínio)
        BigDecimal totalAmount = product.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(totalAmount);
        order.setCustomerName(customer.getName());
        order.setProductName(product.getName());
        order.setStatus(Order.OrderStatus.CONFIRMED);
        order = repository.save(order);
        log.info("Passo 3: Pedido criado com ID: {}", order.getId());

        // 4. Reduzir estoque (Product Service)
        log.info("Passo 4: Reduzindo estoque do produto");
        Boolean stockReduced = productClient.reduceStock(
                request.getProductId(), request.getQuantity());

        if (!stockReduced) {
            order.setStatus(Order.OrderStatus.CANCELLED);
            repository.save(order);
            throw new RuntimeException("Falha ao reduzir estoque");
        }

        // 5. Processar Pagamento (Payment Service)
        log.info("Passo 5: Processando pagamento");
        order.setStatus(Order.OrderStatus.PAYMENT_PENDING);
        repository.save(order);

        PaymentRequest paymentRequest = new PaymentRequest(
                order.getId(), totalAmount, request.getPaymentMethod());
        PaymentDTO payment = paymentClient.processPayment(paymentRequest);

        if ("APPROVED".equals(payment.getStatus())) {
            order.setStatus(Order.OrderStatus.PAID);
            order.setPaymentStatus("APPROVED");
        } else if ("PENDING_RETRY".equals(payment.getStatus())) {
            order.setStatus(Order.OrderStatus.PAYMENT_PENDING);
            order.setPaymentStatus("PENDING_RETRY (Fallback ativado)");
        } else {
            order.setStatus(Order.OrderStatus.CANCELLED);
            order.setPaymentStatus("REJECTED");
        }

        order.setUpdatedAt(LocalDateTime.now());
        order = repository.save(order);

        log.info("=== PEDIDO FINALIZADO - ID: {} | Status: {} | Pagamento: {} ===",
                order.getId(), order.getStatus(), order.getPaymentStatus());

        return order;
    }

    public Page<?> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public Page<?> findByCustomerId(Long customerId, Pageable pageable) {
        return repository.findByCustomerId(customerId, pageable);
    }
}
