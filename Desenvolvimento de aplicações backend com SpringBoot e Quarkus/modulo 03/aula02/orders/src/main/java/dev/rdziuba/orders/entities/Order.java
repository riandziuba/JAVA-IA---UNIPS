package dev.rdziuba.orders.entities;

import dev.rdziuba.orders.entities.Customer;
import dev.rdziuba.orders.entities.ItemOrder;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`Order`")
public class Order extends PanacheEntity {

  public LocalDateTime dateTime;

  @Enumerated(EnumType.STRING)
  public OrderStatus status;

  @Embedded
  public Customer customer;

  @OneToMany(mappedBy = "order")
  public List<ItemOrder> orderItems;

  @Override
  public String toString() {
    return "Order{" +
            "id=" + id +
            ", status=" + status +
            '}';
  }
}
