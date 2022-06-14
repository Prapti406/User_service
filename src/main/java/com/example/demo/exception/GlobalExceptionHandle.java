package com.example.demo.exception;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class GlobalExceptionHandle {
	
	//handle specific exception
		@ExceptionHandler(RecordNotFoundException.class)
		public ResponseEntity<?> handleResourceNotFoundException
		(RecordNotFoundException exception,WebRequest request){
			ExceptionDetails exc= new ExceptionDetails(new Date(),exception.getMessage(),request.getDescription(false));
			return new ResponseEntity(exc,HttpStatus.NOT_FOUND);
		}
	/*
	 * //handle specific exception
	 * 
	 * @ExceptionHandler(APIExceptions.class) public ResponseEntity<?>
	 * handleApiException (APIExceptions exception,WebRequest request){
	 * ExceptionDetails exc= new ExceptionDetails(new
	 * Date(),exception.getMessage(),request.getDescription(false)); return new
	 * ResponseEntity(exc,HttpStatus.NOT_FOUND); }
	 */
				
		//handle global exception
		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> handleGlobalException
		(Exception exception,WebRequest request){
			ExceptionDetails exc= new ExceptionDetails(new Date(),exception.getMessage(),request.getDescription(false));
			return new ResponseEntity(exc,HttpStatus.INTERNAL_SERVER_ERROR);
		
		}

}