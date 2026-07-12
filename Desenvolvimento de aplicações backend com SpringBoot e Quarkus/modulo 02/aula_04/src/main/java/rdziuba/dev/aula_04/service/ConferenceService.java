package rdziuba.dev.aula_04.service;

import org.springframework.stereotype.Service;
import rdziuba.dev.aula_04.model.Conference;
import rdziuba.dev.aula_04.repository.ConferenceRepository;
import rdziuba.dev.aula_04.exceptions.NotFoundException;

import java.util.List;

@Service
public class ConferenceService implements IConferenceService{

    private ConferenceRepository repository;

    public ConferenceService(ConferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Conference addConference(Conference conference) {
        return repository.save(conference);
    }

    @Override
    public Conference getConferenceById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Conference " + id + " not found"));
    }

    @Override
    public List<Conference> getAllConferences() {
        return repository.findAll();
    }
}
