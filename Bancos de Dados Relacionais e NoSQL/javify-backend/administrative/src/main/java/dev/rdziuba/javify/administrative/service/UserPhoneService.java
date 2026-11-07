package dev.rdziuba.javify.administrative.service;

import dev.rdziuba.javify.administrative.domain.UserPhone;
import dev.rdziuba.javify.administrative.dto.UserPhoneRequest;
import dev.rdziuba.javify.administrative.dto.UserPhoneResponse;
import dev.rdziuba.javify.administrative.repository.UserRepository;
import dev.rdziuba.javify.administrative.repository.UserPhoneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserPhoneService {

    private final UserPhoneRepository userPhoneRepository;
    private final UserRepository userRepository;

    public UserPhoneService(UserPhoneRepository userPhoneRepository,
                                  UserRepository userRepository) {
        this.userPhoneRepository = userPhoneRepository;
        this.userRepository = userRepository;
    }

    public List<UserPhoneResponse> findAll() {
        return userPhoneRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public UserPhoneResponse findById(Integer id) {
        return userPhoneRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Phone not found: " + id));
    }

    public List<UserPhoneResponse> findByUser(Long userId) {
        return userPhoneRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public UserPhoneResponse create(UserPhoneRequest request) {
        return create(request.userId(), request);
    }

    @Transactional
    public UserPhoneResponse create(Long userId, UserPhoneRequest request) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
        var phone = new UserPhone();
        phone.setUser(user);
        phone.setNumber(request.number());
        phone.setType(request.type());
        return toResponse(userPhoneRepository.save(phone));
    }

    @Transactional
    public UserPhoneResponse update(Integer id, UserPhoneRequest request) {
        var phone = userPhoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phone not found: " + id));
        var user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + request.userId()));
        phone.setUser(user);
        phone.setNumber(request.number());
        phone.setType(request.type());
        return toResponse(userPhoneRepository.save(phone));
    }

    @Transactional
    public void delete(Integer id) {
        if (!userPhoneRepository.existsById(id)) {
            throw new EntityNotFoundException("Phone not found: " + id);
        }
        userPhoneRepository.deleteById(id);
    }

    private UserPhoneResponse toResponse(UserPhone phone) {
        return new UserPhoneResponse(
                phone.getId(),
                phone.getUser().getId(),
                phone.getNumber(),
                phone.getType().name()
        );
    }
}
