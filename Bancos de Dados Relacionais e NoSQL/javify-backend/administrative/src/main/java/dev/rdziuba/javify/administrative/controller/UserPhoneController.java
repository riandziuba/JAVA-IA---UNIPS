package dev.rdziuba.javify.administrative.controller;

import dev.rdziuba.javify.administrative.dto.UserPhoneRequest;
import dev.rdziuba.javify.administrative.dto.UserPhoneResponse;
import dev.rdziuba.javify.administrative.service.UserPhoneService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserPhoneController {

    private final UserPhoneService userPhoneService;

    public UserPhoneController(UserPhoneService userPhoneService) {
        this.userPhoneService = userPhoneService;
    }

    @GetMapping("/api/phones")
    public ResponseEntity<List<UserPhoneResponse>> list() {
        return ResponseEntity.ok(userPhoneService.findAll());
    }

    @GetMapping("/api/phones/{id}")
    public ResponseEntity<UserPhoneResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userPhoneService.findById(id));
    }

    @GetMapping("/api/users/{userId}/phones")
    public ResponseEntity<List<UserPhoneResponse>> findByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userPhoneService.findByUser(userId));
    }

    @PostMapping("/api/phones")
    public ResponseEntity<UserPhoneResponse> create(@RequestBody @Valid UserPhoneRequest request) {
        var response = userPhoneService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/api/users/{userId}/phones")
    public ResponseEntity<UserPhoneResponse> createForUser(@PathVariable Long userId,
                                                                   @RequestBody @Valid UserPhoneRequest request) {
        var response = userPhoneService.create(userId, request);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/phones/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/api/phones/{id}")
    public ResponseEntity<UserPhoneResponse> update(@PathVariable Integer id,
                                                             @RequestBody @Valid UserPhoneRequest request) {
        return ResponseEntity.ok(userPhoneService.update(id, request));
    }

    @DeleteMapping("/api/phones/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userPhoneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
