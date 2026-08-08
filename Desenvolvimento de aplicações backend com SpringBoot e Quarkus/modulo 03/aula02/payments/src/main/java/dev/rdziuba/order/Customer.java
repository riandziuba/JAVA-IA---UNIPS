package mx.florinda.pedido;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Customer {

  @Column(name = "custumerName")
  public String nome;

  @Column(name = "custumerCPF")
  public String cpf;

  @Column(name = "custumerCelphone")
  public String celular;

  @Column(name = "custumerAdress")
  public String endereco;

}
