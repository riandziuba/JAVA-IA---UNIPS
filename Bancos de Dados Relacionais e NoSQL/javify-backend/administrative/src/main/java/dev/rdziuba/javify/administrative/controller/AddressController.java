package dev.rdziuba.javify.administrative.controller;

import dev.rdziuba.javify.administrative.dto.AddressRequest;
import dev.rdziuba.javify.administrative.dto.AddressResponse;
import dev.rdziuba.javify.administrative.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> list() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{postalCode}")
    public ResponseEntity<AddressResponse> findById(@PathVariable String postalCode) {
        return ResponseEntity.ok(addressService.findByPostalCode(postalCode));
    }

    @PostMapping
    public ResponseEntity<AddressResponse> create(@RequestBody @Valid AddressRequest request) {
        var response = addressService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postalCode}")
                .buildAndExpand(response.postalCode())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{postalCode}")
    public ResponseEntity<AddressResponse> update(@PathVariable String postalCode,
                                                      @RequestBody @Valid AddressRequest request) {
        return ResponseEntity.ok(addressService.update(postalCode, request));
    }

    @DeleteMapping("/{postalCode}")
    public ResponseEntity<Void> delete(@PathVariable String postalCode) {
        addressService.delete(postalCode);
        return ResponseEntity.noContent().build();
    }
}
