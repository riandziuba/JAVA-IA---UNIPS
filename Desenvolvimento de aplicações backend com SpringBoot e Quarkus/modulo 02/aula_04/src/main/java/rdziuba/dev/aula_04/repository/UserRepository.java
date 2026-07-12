package rdziuba.dev.aula_04.repository;

import org.springframework.data.repository.ListCrudRepository;
import rdziuba.dev.aula_04.model.User;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
