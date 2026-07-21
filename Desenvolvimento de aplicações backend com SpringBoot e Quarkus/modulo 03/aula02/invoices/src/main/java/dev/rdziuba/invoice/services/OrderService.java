package dev.rdziuba.invoice.services;

import dev.rdziuba.invoice.entities.OrderDto;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.math.BigDecimal;

@Path("/orders")
@RegisterRestClient
public interface OrderService {

  @GET
  @Path("/{id}")
  Uni<OrderDto> getById(@PathParam("id") Long orderId);

  default Uni<String> invoice(Long orderId, BigDecimal price) {
    return getById(orderId).onItem().transform(order -> """
            <xml>
              <price>%s</price>
              <customer>
                <name>%s</name>
                <document>%s</document>
                <phone>%s</phone>
                <address>%s</address>
              </customer>
            </xml>
            """.formatted(price, order.customer.name, order.customer.document, order.customer.phone, order.customer.address));
  }
}
