package com.example.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.customExceptions.CustomExceptions;

@RestControllerAdvice
public class Exceptionss {
	
	@ExceptionHandler(CustomExceptions.class)
	public ResponseEntity<ApiError> handlerException(CustomExceptions id){
		System.out.println("Global Exception");
		ApiError ae = new ApiError();
		ae.setResponse(id.getMessage());
		ae.setSuccess(false);
		return new ResponseEntity<ApiError>(ae, HttpStatus.OK);
      }
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ApiError> nullPointerExceptionHandling(NullPointerException n){
		ApiError ae = new ApiError();
		ae.setResponse("Nullpointer Exception");
		ae.setSuccess(false);
		return new ResponseEntity<ApiError>(ae, HttpStatus.BAD_REQUEST);
		
	}
}
