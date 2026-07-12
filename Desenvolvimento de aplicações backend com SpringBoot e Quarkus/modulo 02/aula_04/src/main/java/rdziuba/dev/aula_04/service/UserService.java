package rdziuba.dev.aula_04.service;

import org.springframework.stereotype.Service;
import rdziuba.dev.aula_04.exceptions.NotFoundException;
import rdziuba.dev.aula_04.model.User;
import rdziuba.dev.aula_04.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements IUserService{

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User addUser(User conference) {
        return repository.save(conference);
    }

    @Override
    public User getUserById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " not registered"));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
