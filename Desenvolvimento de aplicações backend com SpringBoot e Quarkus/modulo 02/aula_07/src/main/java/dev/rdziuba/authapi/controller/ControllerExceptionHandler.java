package dev.rdziuba.authapi.controller;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<String> handleRuntime(ExpiredJwtException ex){
		return ResponseEntity.status(403).body("Token JWT expirado");
	}

	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<String> handleRuntime(InvalidParameterException ex){
		return ResponseEntity.status(403).body(ex.getMessage());
	}

}
