package rdziuba.dev.aula_04.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rdziuba.dev.aula_04.model.User;
import rdziuba.dev.aula_04.service.IUserService;

import java.util.List;

@RestController
public class UserController {
    private IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User session) {
        return ResponseEntity.status(201).body(service.addUser(session));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(200).body(service.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.status(200).body(service.getUserById(id));
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(service.getUserByEmail(email));
    }
}
