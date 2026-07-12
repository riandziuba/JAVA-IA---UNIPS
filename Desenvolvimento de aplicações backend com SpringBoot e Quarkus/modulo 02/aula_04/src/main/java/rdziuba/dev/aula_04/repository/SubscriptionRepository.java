package rdziuba.dev.aula_04.repository;

import org.springframework.data.repository.ListCrudRepository;
import rdziuba.dev.aula_04.model.Session;
import rdziuba.dev.aula_04.model.Subscription;
import rdziuba.dev.aula_04.model.SubscriptionID;
import rdziuba.dev.aula_04.model.User;

import java.util.List;

public interface SubscriptionRepository extends ListCrudRepository<Subscription, SubscriptionID> {
   public List<Subscription> findByIdUser(User user);
   public List<Subscription> findByIdSession(Session session);
}
