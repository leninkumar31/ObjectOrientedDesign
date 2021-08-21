package com.leninkumar.cabbooking.exceptions;

public class UserDoesnotExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6707547507796245819L;

	public UserDoesnotExistException(String msg) {
		super(msg);
	}
}
