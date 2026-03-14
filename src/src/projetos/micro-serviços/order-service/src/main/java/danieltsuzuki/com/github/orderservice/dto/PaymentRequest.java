package danieltsuzuki.com.github.orderservice.dto;

import java.math.BigDecimal;

public class PaymentRequest {
    private Long orderId;
    private BigDecimal amount;
    private String method;

    public PaymentRequest() {}

    public PaymentRequest(Long orderId, BigDecimal amount, String method) {
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
