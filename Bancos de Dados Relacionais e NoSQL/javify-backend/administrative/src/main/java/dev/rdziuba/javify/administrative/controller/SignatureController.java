package dev.rdziuba.javify.administrative.controller;

import dev.rdziuba.javify.administrative.dto.SignatureRequest;
import dev.rdziuba.javify.administrative.dto.SignatureResponse;
import dev.rdziuba.javify.administrative.service.SignatureService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/signatures")
public class SignatureController {

    private final SignatureService signatureService;

    public SignatureController(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    @GetMapping
    public ResponseEntity<List<SignatureResponse>> list() {
        return ResponseEntity.ok(signatureService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignatureResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(signatureService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SignatureResponse> create(@RequestBody @Valid SignatureRequest request) {
        var response = signatureService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SignatureResponse> update(@PathVariable Integer id,
                                                        @RequestBody @Valid SignatureRequest request) {
        return ResponseEntity.ok(signatureService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        signatureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
