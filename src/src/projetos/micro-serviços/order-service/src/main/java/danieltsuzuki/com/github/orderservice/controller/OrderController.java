package danieltsuzuki.com.github.orderservice.controller;

import danieltsuzuki.com.github.orderservice.dto.OrderRequest;
import danieltsuzuki.com.github.orderservice.entity.Order;
import danieltsuzuki.com.github.orderservice.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getByCustomerId(@PathVariable Long customerId, @PageableDefault Pageable pageable) {
        Page<?> orders = service.findByCustomerId(customerId, pageable);
        if (orders.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(orders);
    }

    /**
     * ENDPOINT PRINCIPAL - Fluxo completo que toca todos os microsserviços:
     * Customer Service -> Product Service -> Order Service -> Payment Service
     */
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            Order order = service.createOrder(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
