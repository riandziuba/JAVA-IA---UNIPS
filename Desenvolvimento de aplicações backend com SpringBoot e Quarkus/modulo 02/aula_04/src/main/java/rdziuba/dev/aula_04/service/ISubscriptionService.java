package rdziuba.dev.aula_04.service;

import rdziuba.dev.aula_04.model.Session;
import rdziuba.dev.aula_04.model.Subscription;
import rdziuba.dev.aula_04.model.User;

import java.util.List;

public interface ISubscriptionService {
    public Subscription addSubscription(Subscription subscription);
    public List<Subscription> getAllByUser(User user);
    public List<Subscription> getAllBySession(Session session);
}
