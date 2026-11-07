package dev.rdziuba.javify.administrative.service;

import dev.rdziuba.javify.administrative.domain.Address;
import dev.rdziuba.javify.administrative.dto.AddressRequest;
import dev.rdziuba.javify.administrative.dto.AddressResponse;
import dev.rdziuba.javify.administrative.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressResponse> findAll() {
        return addressRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public AddressResponse findByPostalCode(String postalCode) {
        return addressRepository.findById(postalCode)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Address not found: " + postalCode));
    }

    @Transactional
    public AddressResponse create(AddressRequest request) {
        var address = new Address();
        address.setPostalCode(request.postalCode());
        address.setStreet(request.street());
        address.setNeighborhood(request.neighborhood());
        return toResponse(addressRepository.save(address));
    }

    @Transactional
    public AddressResponse update(String postalCode, AddressRequest request) {
        var address = addressRepository.findById(postalCode)
                .orElseThrow(() -> new EntityNotFoundException("Address not found: " + postalCode));
        address.setStreet(request.street());
        address.setNeighborhood(request.neighborhood());
        return toResponse(addressRepository.save(address));
    }

    @Transactional
    public void delete(String postalCode) {
        if (!addressRepository.existsById(postalCode)) {
            throw new EntityNotFoundException("Address not found: " + postalCode);
        }
        addressRepository.deleteById(postalCode);
    }

    AddressResponse toResponse(Address address) {
        if (address == null) {
            return null;
        }
        return new AddressResponse(
                address.getPostalCode(),
                address.getStreet(),
                address.getNeighborhood()
        );
    }
}
