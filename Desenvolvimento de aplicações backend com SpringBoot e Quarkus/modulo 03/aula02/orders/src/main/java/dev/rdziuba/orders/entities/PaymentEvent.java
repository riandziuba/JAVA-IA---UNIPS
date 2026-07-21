package dev.rdziuba.orders.entities;

public class PaymentEvent {
    public Long paymentId;
    public Long orderId;

    @Override
    public String toString() {
        return "PaymentEvent{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                '}';
    }
}
