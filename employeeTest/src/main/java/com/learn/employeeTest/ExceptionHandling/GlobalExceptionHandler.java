package com.learn.employeeTest.ExceptionHandling;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
	        Map<String, Object> errorBody = new HashMap<>();
	        errorBody.put("timestamp", LocalDateTime.now());
	        errorBody.put("status", HttpStatus.NOT_FOUND.value());
	        errorBody.put("message", ex.getMessage());

	        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	    }
}
