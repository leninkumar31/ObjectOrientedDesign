package com.example.todo.exceptions;

public class ResourceAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException(String msg) {
		super(msg);
	}
}
