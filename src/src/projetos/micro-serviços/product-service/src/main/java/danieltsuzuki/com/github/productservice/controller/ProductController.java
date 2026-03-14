package danieltsuzuki.com.github.productservice.controller;

import danieltsuzuki.com.github.productservice.entity.Product;
import danieltsuzuki.com.github.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
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

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                          @Valid @RequestBody Product product) {
        return service.findById(id).map(existing -> {
            product.setId(id);
            return ResponseEntity.ok(service.save(product));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/reduce-stock")
    public ResponseEntity<?> reduceStock(@PathVariable Long id,
                                               @RequestParam int quantity) {
        boolean result = service.reduceStock(id, quantity);
        return result ? ResponseEntity.ok(true)
                : ResponseEntity.badRequest().body(false);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
