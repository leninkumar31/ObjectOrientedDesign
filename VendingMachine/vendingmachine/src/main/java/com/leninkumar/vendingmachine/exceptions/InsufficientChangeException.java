package com.leninkumar.vendingmachine.exceptions;

public class InsufficientChangeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 839244822168857387L;

	public InsufficientChangeException(String msg) {
		super(msg);
	}
}
