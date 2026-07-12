package rdziuba.dev.aula_04.service;

import rdziuba.dev.aula_04.model.User;

import java.util.List;

public interface IUserService {
    public User addUser(User conference);
    public User getUserById(Integer id);
    public User getUserByEmail(String email);
    public List<User> getAllUsers();
}
