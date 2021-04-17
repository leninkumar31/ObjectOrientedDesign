package com.example.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserNameAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserNameAlreadyExistsException() {
		super("UserName already exists. Please use something else");
	}

	public UserNameAlreadyExistsException(String msg) {
		super(msg);
	}
}
