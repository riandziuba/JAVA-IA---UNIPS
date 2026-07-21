package dev.rdziuba.invoice.consumers;

import dev.rdziuba.invoice.entities.PaymentEvent;
import dev.rdziuba.invoice.services.OrderService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ConfirmedPaymentConsumer {

    private static final Logger LOG = Logger.getLogger(ConfirmedPaymentConsumer.class);
    private final OrderService orderService;

    @Inject
    public ConfirmedPaymentConsumer(@RestClient OrderService orderService) {
        this.orderService = orderService;
    }

    @Incoming("paymentsConfirmed")
    public Uni<Void> consume(PaymentEvent event) {
        LOG.infof("Pagamento confirmado recebido: %s", event);

        return orderService.invoice(event.orderId, event.price)
                .onItem()
                .invoke(xml -> System.out.println(xml))
                .replaceWithVoid();
    }
}
