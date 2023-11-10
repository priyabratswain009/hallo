package com.sunknowledge.changehealthcare.pojo.professionalclaims;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(KeyNotFoundException.class)
    public ResponseEntity<ExceptionResponse> keyNotFound(KeyNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString().substring(0,3));
        response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
}
