package dev.rdziuba.authapi.service;

import dev.rdziuba.authapi.model.User;
import dev.rdziuba.authapi.security.MyToken;

public interface IUserService {
	public User addUser(User user);
	public User getByUsername(String username);
	public MyToken userLogin(User user);
}
