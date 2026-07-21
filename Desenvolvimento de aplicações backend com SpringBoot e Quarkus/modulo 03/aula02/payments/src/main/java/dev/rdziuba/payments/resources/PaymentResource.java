package dev.rdziuba.payments.resources;

import dev.rdziuba.payments.entities.Payment;
import dev.rdziuba.payments.entities.PaymentEvent;
import dev.rdziuba.payments.entities.StatusPayment;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.List;

@Path("/payment")
public class PaymentResource {

  @Inject
  @Channel("paymentsConfirmed")
  Emitter<PaymentEvent> emitter;

  @GET
  public Uni<List<Payment>> list() {
    return Payment.listAll();
  }

  @GET
  @Path("/{id}")
  public Uni<Payment> byId(Long id) {
    return Payment.findById(id);
  }

  @PUT
  @Path("/{id}")
  public  Uni<Payment> confirm(Long id) {
    return Panache.withTransaction(() ->
            Payment.<Payment>findById(id)
                    .onItem().ifNotNull().invoke(payment -> {
                      payment.status = StatusPayment.CONFIRMED;
                      PaymentEvent event = new PaymentEvent(payment.id, payment.orderId, payment.price);
                      emitter.send(event);
                    }));
  }

}
