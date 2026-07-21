package dev.rdziuba.orders.consumers;

import dev.rdziuba.orders.entities.Order;
import dev.rdziuba.orders.entities.OrderStatus;
import dev.rdziuba.orders.entities.PaymentEvent;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ConfirmedPaymentConsumer {

    private static final Logger LOG = Logger.getLogger(ConfirmedPaymentConsumer.class);

    @Incoming("paymentsConfirmed")
    public Uni<Void> consume(PaymentEvent event) {
        LOG.infof("Pagamento confirmado recebido: %s", event);

        return Panache.withTransaction(() -> Order.<Order>findById(event.orderId)
                .onItem()
                .ifNotNull()
                .invoke(order -> {
                    order.status = OrderStatus.PAID;
                    LOG.infof("Pedido %d atualizado para PAID", order.id);
                }))
                .replaceWithVoid();
    }
}
