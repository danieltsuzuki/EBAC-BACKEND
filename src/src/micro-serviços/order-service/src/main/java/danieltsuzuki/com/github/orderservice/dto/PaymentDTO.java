package danieltsuzuki.com.github.orderservice.dto;

import java.math.BigDecimal;

public class PaymentDTO {
    private Long id;
    private Long orderId;
    private BigDecimal amount;
    private String status;
    private String method;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
