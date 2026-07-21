package dev.rdziuba.payments.entities;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

@Entity
public class Payment extends PanacheEntity {

  public BigDecimal price;

  @Enumerated(EnumType.STRING)
  public StatusPayment status;

  public Long orderId;

  @Override
  public String toString() {
    return "Payment{" +
            "id=" + id +
            ", orderId=" + orderId +
            ", status=" + status +
            ", price=" + price +
            '}';
  }
}
