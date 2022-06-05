package com.recipe.advice;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.recipe.exception.CustomerNotFoundException;
import com.recipe.exception.ErrorDetails;
import com.recipe.exception.InvalidTokenException;
import com.recipe.exception.InvalidUserException;
import com.recipe.exception.RecipeNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandling extends ResponseEntityExceptionHandler{

//Exception Handler for recipeNotFound.
	@ExceptionHandler(RecipeNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleException(RecipeNotFoundException exception,WebRequest request){
		ErrorDetails details = new ErrorDetails(exception.getMessage(),LocalDate.now(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details,HttpStatus.NOT_FOUND);
	}
//Exception Handler for Customer not found in the database.	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleException(CustomerNotFoundException exception, WebRequest request){
		ErrorDetails details = new ErrorDetails(exception.getMessage(), LocalDate.now(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);	
	}
	
//Exception Handler for Exceptions occurred during HTTP requests or data entered into database.	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllExceptions(Exception exception, WebRequest request){
		ErrorDetails details = new ErrorDetails(exception.getMessage(), LocalDate.now(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.BAD_REQUEST);
	}
	
//Exception Handler for User not authorized.
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<?> handleUserDataErrors(InvalidUserException ex) {

		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("errorMessage", ex.getMessage());

		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}
//Exception Handler for Token not valid.
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<?> handleTokenErrors(InvalidTokenException ex) {

		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("errorMessage", ex.getMessage());

		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}

}
