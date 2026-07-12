package dev.rdziuba.authapi.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.rdziuba.authapi.model.User;
import dev.rdziuba.authapi.repository.UserRepository;
import dev.rdziuba.authapi.security.MyToken;
import dev.rdziuba.authapi.security.TokenUtil;

import java.security.InvalidParameterException;

@Service
public class UserService implements IUserService{
	
	private UserRepository repository;
	

	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public User addUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user);
	}

	@Override
	public User getByUsername(String username) {
		return null;
	}

	@Override
	public MyToken userLogin(User user) {
		
		User storedUser = repository.findByUsername(user.getUsername()).orElseThrow(()->new RuntimeException("User not found!"));
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(user.getPassword(), storedUser.getPassword())) {
			return TokenUtil.encode(storedUser);
		}
		throw new InvalidParameterException("Unauthorized user");
	}

}
