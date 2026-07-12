package rdziuba.dev.aula_04.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rdziuba.dev.aula_04.model.Session;
import rdziuba.dev.aula_04.service.ISessionService;

import java.util.List;

@RestController
public class SessionController {
    private ISessionService service;

    public SessionController(ISessionService service) {
        this.service = service;
    }

    @PostMapping("/sessions")
    public ResponseEntity<Session> addSession(@RequestBody Session session) {
        return ResponseEntity.status(201).body(service.addSession(session));
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getAllSessions() {
        return ResponseEntity.status(200).body(service.getAllSessions());
    }

    @GetMapping("/sessions/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable int id) {
        return ResponseEntity.status(200).body(service.getSessionById(id));
    }
}
