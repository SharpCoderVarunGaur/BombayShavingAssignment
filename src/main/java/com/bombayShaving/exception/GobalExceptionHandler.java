package com.bombayShaving.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNoFoundException(ResourceNotFoundException e){
		System.out.println("Hello Not found");
		String mss=e.getMessage();
		
		ApiResponse ap=new ApiResponse(mss,false);
		
		return new ResponseEntity<ApiResponse>(ap,HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler(EmailSameException.class)
	public ResponseEntity<ApiResponse> emailSameException(EmailSameException e){
		System.out.println("Hello Not found");
		String mss=e.getMessage();
		
		ApiResponse ap=new ApiResponse(mss,false);
		
		return new ResponseEntity<ApiResponse>(ap,HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodeArgumentNotValidException(MethodArgumentNotValidException e){
		Map<String,String> resp=new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error)->{
		       String fieldName=((FieldError) error).getField();
		       String message=error.getDefaultMessage();
		       resp.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
}

