package rdziuba.dev.aula_04.service;

import rdziuba.dev.aula_04.model.Session;

import java.util.List;

public interface ISessionService {
    public Session addSession(Session conference);
    public Session getSessionById(Integer id);
    public List<Session> getAllSessions();
}
