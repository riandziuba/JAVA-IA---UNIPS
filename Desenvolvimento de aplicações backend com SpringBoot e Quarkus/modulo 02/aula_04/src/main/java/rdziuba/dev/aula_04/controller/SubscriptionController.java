package rdziuba.dev.aula_04.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rdziuba.dev.aula_04.model.Session;
import rdziuba.dev.aula_04.model.Subscription;
import rdziuba.dev.aula_04.model.User;
import rdziuba.dev.aula_04.service.ISubscriptionService;

import java.util.List;

@RestController
public class SubscriptionController {
    private ISubscriptionService service;

    public SubscriptionController(ISubscriptionService service) {
        this.service = service;
    }

    @PostMapping("/subscriptions")
    public ResponseEntity<Subscription> addSubscription(@RequestBody Subscription subscription) {
        return ResponseEntity.status(201).body(service.addSubscription(subscription));
    }

    @GetMapping("/subscriptions/user/{userId}")
    public ResponseEntity<List<Subscription>> getByUserId(@PathVariable(name = "userId") Integer id) {
        User user = new User();
        user.setId(id);
        return ResponseEntity.ok(service.getAllByUser(user));
    }

    @GetMapping("/subscriptions/session/{sessionId}")
    public ResponseEntity<List<Subscription>> getBySessionId(@PathVariable(name = "sessionId") Integer id) {
        Session session = new Session();
        session.setId(id);
        return ResponseEntity.ok(service.getAllBySession(session));
    }
}
