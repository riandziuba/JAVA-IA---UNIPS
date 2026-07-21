package dev.rdziuba.orders.entities;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ItemMenu extends PanacheEntity {

  public String name;
  public String description;

  @Enumerated(EnumType.STRING)
  public MenuCategory category;

  public BigDecimal price;
  public BigDecimal priceWithDiscount;

}
