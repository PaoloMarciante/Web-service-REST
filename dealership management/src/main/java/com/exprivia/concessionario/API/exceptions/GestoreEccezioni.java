package com.exprivia.concessionario.API.exceptions;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.exprivia.concessionario.API.exceptions.model.ErrorModel;

@RestControllerAdvice
public class GestoreEccezioni extends ResponseEntityExceptionHandler {
	
	

	    @ExceptionHandler(EntityNotFoundException.class)
	    private ResponseEntity<ErrorModel> handleEntityNotFound(EntityNotFoundException ex){
	        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND,  "Entity not found", ex.getMessage());

	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(NullPointerException.class)
	    private ResponseEntity<ErrorModel> handleNullPointerException(NullPointerException ex){
	    	ErrorModel error = new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR, "Null Pointer Exception", ex.getMessage());
	    	
	    	return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
            ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Validation Error", ex.getBindingResult().toString());

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
}



