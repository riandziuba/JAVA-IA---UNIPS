package dev.rdziuba.orders.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.rdziuba.orders.entities.ItemMenu;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class ItemOrder extends PanacheEntity {

  public Long amount;
  public BigDecimal unitaryPrice;
  public String observation;

  @JsonIgnore
  @ManyToOne
  public Order order;

  @ManyToOne
  public ItemMenu itemMenu;

}
