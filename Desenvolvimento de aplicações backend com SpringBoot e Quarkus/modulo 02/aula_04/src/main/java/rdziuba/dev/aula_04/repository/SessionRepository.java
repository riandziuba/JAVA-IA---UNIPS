package rdziuba.dev.aula_04.repository;

import org.springframework.data.repository.ListCrudRepository;
import rdziuba.dev.aula_04.model.Session;

public interface SessionRepository extends ListCrudRepository<Session, Integer> {
}
