package dev.rdziuba.payments.entities;

import java.math.BigDecimal;

public class PaymentEvent {
    public Long paymentId;
    public Long orderId;
    public BigDecimal price;

    public PaymentEvent(Long paymentId, Long orderId, BigDecimal price) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.price = price;
    }

    @Override
    public String toString() {
        return "PaymentEvent{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", price=" + price +
                '}';
    }
}
