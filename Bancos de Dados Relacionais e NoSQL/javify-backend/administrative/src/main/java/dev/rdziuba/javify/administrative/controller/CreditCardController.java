package dev.rdziuba.javify.administrative.controller;

import dev.rdziuba.javify.administrative.dto.CreditCardRequest;
import dev.rdziuba.javify.administrative.dto.CreditCardResponse;
import dev.rdziuba.javify.administrative.service.CreditCardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("/api/credit-cards")
    public ResponseEntity<List<CreditCardResponse>> list() {
        return ResponseEntity.ok(creditCardService.findAll());
    }

    @GetMapping("/api/credit-cards/{id}")
    public ResponseEntity<CreditCardResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(creditCardService.findById(id));
    }

    @GetMapping("/api/signatures/{signatureId}/credit-cards")
    public ResponseEntity<List<CreditCardResponse>> findBySignature(@PathVariable Integer signatureId) {
        return ResponseEntity.ok(creditCardService.findBySignature(signatureId));
    }

    @PostMapping("/api/credit-cards")
    public ResponseEntity<CreditCardResponse> create(@RequestBody @Valid CreditCardRequest request) {
        var response = creditCardService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/api/signatures/{signatureId}/credit-cards")
    public ResponseEntity<CreditCardResponse> createForSignature(@PathVariable Integer signatureId,
                                                                   @RequestBody @Valid CreditCardRequest request) {
        var response = creditCardService.create(signatureId, request);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/credit-cards/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/api/credit-cards/{id}")
    public ResponseEntity<CreditCardResponse> update(@PathVariable Integer id,
                                                           @RequestBody @Valid CreditCardRequest request) {
        return ResponseEntity.ok(creditCardService.update(id, request));
    }

    @DeleteMapping("/api/credit-cards/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        creditCardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
