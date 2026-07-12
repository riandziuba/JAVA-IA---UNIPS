package rdziuba.dev.aula_04.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rdziuba.dev.aula_04.dto.ErrorDTO;
import rdziuba.dev.aula_04.exceptions.NotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(exception = NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(404).body(new ErrorDTO(exception.getMessage()));
    }
}
