package dev.rdziuba.authapi.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import dev.rdziuba.authapi.model.User;

public interface UserRepository extends ListCrudRepository<User, Integer>{

	public Optional<User> findByUsername(String username);
}
