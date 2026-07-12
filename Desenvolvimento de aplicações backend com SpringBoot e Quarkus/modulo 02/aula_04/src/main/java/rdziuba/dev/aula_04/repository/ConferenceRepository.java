package rdziuba.dev.aula_04.repository;

import org.springframework.data.repository.ListCrudRepository;
import rdziuba.dev.aula_04.model.Conference;

public interface ConferenceRepository extends ListCrudRepository<Conference, Integer> {
}
