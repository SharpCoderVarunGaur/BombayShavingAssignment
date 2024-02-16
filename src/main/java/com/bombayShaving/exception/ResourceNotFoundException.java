package com.bombayShaving.exception;

import org.springframework.stereotype.Component;

@Component
public class ResourceNotFoundException extends RuntimeException{
	  
	private String mssg;
	private String fieldName;
	private long fieldValue;
	
	public ResourceNotFoundException() {
		super("Resource Not Found");
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String mssg ,String fieldName, int fieldValue) {
        super(mssg+" "+fieldName+""+fieldValue);
        this.mssg=mssg;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
	
	}
    
}
