package rdziuba.dev.aula_04.service;

import org.springframework.stereotype.Service;
import rdziuba.dev.aula_04.model.Session;
import rdziuba.dev.aula_04.model.Subscription;
import rdziuba.dev.aula_04.model.User;
import rdziuba.dev.aula_04.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionService implements ISubscriptionService{

    private SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subscription addSubscription(Subscription subscription) {
        subscription.setCreatedAt(LocalDateTime.now());
        subscription.setUniqueID(UUID.randomUUID().toString());
        return repository.save(subscription);
    }

    @Override
    public List<Subscription> getAllByUser(User user) {
        return repository.findByIdUser(user);
    }

    @Override
    public List<Subscription> getAllBySession(Session session) {
        return repository.findByIdSession(session);
    }
}
