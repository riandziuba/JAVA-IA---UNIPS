package dev.rdziuba.javify.administrative.service;

import dev.rdziuba.javify.administrative.domain.Signature;
import dev.rdziuba.javify.administrative.domain.Address;
import dev.rdziuba.javify.administrative.domain.User;
import dev.rdziuba.javify.administrative.dto.*;
import dev.rdziuba.javify.administrative.repository.SignatureRepository;
import dev.rdziuba.javify.administrative.repository.AddressRepository;
import dev.rdziuba.javify.administrative.repository.PlanRepository;
import dev.rdziuba.javify.administrative.repository.UserRepository;
import dev.rdziuba.javify.administrative.repository.UserPhoneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PlanRepository planRepository;
    private final SignatureRepository signatureRepository;
    private final UserPhoneRepository userPhoneRepository;

    public UserService(UserRepository userRepository,
                          AddressRepository addressRepository,
                          PlanRepository planRepository,
                          SignatureRepository signatureRepository,
                          UserPhoneRepository userPhoneRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.planRepository = planRepository;
        this.signatureRepository = signatureRepository;
        this.userPhoneRepository = userPhoneRepository;
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(this::toSummaryResponse)
                .toList();
    }

    public UserResponse findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        return toFullResponse(user);
    }

    @Transactional
    public UserResponse create(UserRequest request) {
        var user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(request.passwordHash());
        user.setHolder(request.holder());
        user.setBirthday(request.birthday());
        linkAddress(user, request.address());
        linkSignature(user, request.planId(), request.signatureId());
        return toFullResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse update(Long id, UserRequest request) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(request.passwordHash());
        user.setHolder(request.holder());
        user.setBirthday(request.birthday());
        linkAddress(user, request.address());
        linkSignature(user, request.planId(), request.signatureId());
        return toFullResponse(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found: " + id);
        }
        userRepository.deleteById(id);
    }

    private void linkAddress(User user, AddressRequest request) {
        var address = addressRepository.findById(request.postalCode())
                .orElseGet(() -> {
                    var newAddress = new Address();
                    newAddress.setPostalCode(request.postalCode());
                    newAddress.setStreet(request.street());
                    newAddress.setNeighborhood(request.neighborhood());
                    return addressRepository.save(newAddress);
                });
        user.setAddress(address);
    }

    private void linkSignature(User user, Integer planId, Integer signatureId) {
        if (planId != null) {
            var plan = planRepository.findById(planId)
                    .orElseThrow(() -> new EntityNotFoundException("Plan not found: " + planId));
            var signature = new Signature();
            signature.setPlan(plan);
            signature.setActive(true);
            user.setSignature(signatureRepository.save(signature));
            return;
        }
        if (signatureId != null) {
            var signature = signatureRepository.findById(signatureId)
                    .orElseThrow(() -> new EntityNotFoundException("Signature not found: " + signatureId));
            user.setSignature(signature);
            return;
        }
        user.setSignature(null);
    }

    private UserResponse toSummaryResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isHolder(),
                user.getBirthday(),
                toAddressResponse(user.getAddress()),
                toSignatureResponse(user.getSignature()),
                List.of()
        );
    }

    private UserResponse toFullResponse(User user) {
        var phones = userPhoneRepository.findByUserId(user.getId()).stream()
                .map(t -> new UserPhoneResponse(t.getId(), t.getUser().getId(), t.getNumber(), t.getType().name()))
                .toList();
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isHolder(),
                user.getBirthday(),
                toAddressResponse(user.getAddress()),
                toSignatureResponse(user.getSignature()),
                phones
        );
    }

    private AddressResponse toAddressResponse(Address address) {
        if (address == null) {
            return null;
        }
        return new AddressResponse(
                address.getPostalCode(),
                address.getStreet(),
                address.getNeighborhood()
        );
    }

    private SignatureResponse toSignatureResponse(Signature signature) {
        if (signature == null) {
            return null;
        }
        var plan = signature.getPlan();
        var planResponse = plan != null ? new PlanResponse(
                plan.getId(),
                plan.getName(),
                plan.getPrice(),
                plan.isHasAdvertising(),
                plan.getMembersLimit(),
                plan.isOfflineMode()
        ) : null;
        return new SignatureResponse(
                signature.getId(),
                planResponse,
                signature.isActive()
        );
    }
}
