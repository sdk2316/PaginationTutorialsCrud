package com.durgesh.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
  
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	 // ErrorMessage message = new ErrorMessage(new Date(), ex.getMessage(),  request.getDescription(false));
		       
		      
    
    return new ResponseEntity<ErrorMessage>( HttpStatus.NOT_FOUND);
  }
}