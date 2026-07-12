package dev.rdziuba.authapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
	
	@GetMapping("/open")
	public String sayHelloOpen() {
		return "Hello world from open endpoint";
	}
	
	@GetMapping("/restricted")
	public String sayHelloRestricted() {
		return "Hello world from restricted endpoint";
	}

}
