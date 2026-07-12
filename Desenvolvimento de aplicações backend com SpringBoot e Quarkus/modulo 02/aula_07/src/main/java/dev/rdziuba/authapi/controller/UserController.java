package dev.rdziuba.authapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.rdziuba.authapi.model.User;
import dev.rdziuba.authapi.security.MyToken;
import dev.rdziuba.authapi.service.IUserService;

@RestController
public class UserController {
	
	private IUserService service;
	
	
	public UserController(IUserService service) {
		super();
		this.service = service;
	}


	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user){
		return ResponseEntity.status(201).body(service.addUser(user));
	}
	
	@PostMapping("/login")
	public ResponseEntity<MyToken> loging(@RequestBody User user){
		return ResponseEntity.ok(service.userLogin(user));
	}

}
