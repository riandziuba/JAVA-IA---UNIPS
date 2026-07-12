package rdziuba.dev.aula_04.service;

import org.springframework.stereotype.Service;
import rdziuba.dev.aula_04.exceptions.NotFoundException;
import rdziuba.dev.aula_04.model.Session;
import rdziuba.dev.aula_04.repository.SessionRepository;

import java.util.List;

@Service
public class SessionService implements ISessionService{

    private SessionRepository repository;

    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Session addSession(Session conference) {
        return repository.save(conference);
    }

    @Override
    public Session getSessionById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Session " + id + " not found"));
    }

    @Override
    public List<Session> getAllSessions() {
        return repository.findAll();
    }
}
