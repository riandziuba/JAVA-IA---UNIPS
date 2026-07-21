package dev.rdziuba.orders.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Customer {

  @Column(name = "customerName")
  public String name;

  @Column(name = "customerDocument")
  public String document;

  @Column(name = "customerPhone")
  public String phone;

  @Column(name = "customerAddress")
  public String address;

}
