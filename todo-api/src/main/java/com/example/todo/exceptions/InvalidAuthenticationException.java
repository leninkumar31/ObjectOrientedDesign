package com.example.todo.exceptions;

public class InvalidAuthenticationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidAuthenticationException() {
		super("Invalid Username or Password");
	}

	public InvalidAuthenticationException(String msg) {
		super(msg);
	}
}
