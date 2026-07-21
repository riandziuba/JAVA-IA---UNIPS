package dev.rdziuba.orders.resources;

import dev.rdziuba.orders.entities.Order;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/orders")
public class OrderResource {

  @GET
  public Uni<List<Order>> list() {
    return Order.find("SELECT DISTINCT p FROM Order p LEFT JOIN FETCH p.orderItems").list();
  }

  @GET
  @Path("/{id}")
  public Uni<Order> byId(Long id) {
    return Order.find("SELECT p FROM Order p LEFT JOIN FETCH p.orderItems WHERE p.id = :id", Parameters.with("id", id))
            .firstResult();
  }

}
