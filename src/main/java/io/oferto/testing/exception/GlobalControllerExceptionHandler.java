package io.oferto.testing.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleProductNotFound(RuntimeException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleException(ConstraintViolationException e){
		return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
