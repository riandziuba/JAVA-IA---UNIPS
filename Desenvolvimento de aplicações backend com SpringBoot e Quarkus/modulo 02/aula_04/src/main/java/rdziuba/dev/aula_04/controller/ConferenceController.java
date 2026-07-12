package rdziuba.dev.aula_04.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rdziuba.dev.aula_04.model.Conference;
import rdziuba.dev.aula_04.service.IConferenceService;

import java.util.List;

@RestController
public class ConferenceController {
    private IConferenceService service;

    public ConferenceController(IConferenceService service) {
        this.service = service;
    }

    @PostMapping("/conferences")
    public ResponseEntity<Conference> addConference(@RequestBody Conference session) {
        return ResponseEntity.status(201).body(service.addConference(session));
    }

    @GetMapping("/conferences")
    public ResponseEntity<List<Conference>> getAllConferences() {
        return ResponseEntity.status(200).body(service.getAllConferences());
    }

    @GetMapping("/conferences/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable int id) {
        return ResponseEntity.status(200).body(service.getConferenceById(id));
    }
}
