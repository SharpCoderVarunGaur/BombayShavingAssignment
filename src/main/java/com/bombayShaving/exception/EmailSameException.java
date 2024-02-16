package com.bombayShaving.exception;

public class EmailSameException extends RuntimeException {
	private String mssg;
	
	
	public EmailSameException() {
		super("Email is Alredy Exsist");
		// TODO Auto-generated constructor stub
	}

	public EmailSameException(String mssg) {
        super(mssg);
        this.mssg=mssg;
	
	}
}
