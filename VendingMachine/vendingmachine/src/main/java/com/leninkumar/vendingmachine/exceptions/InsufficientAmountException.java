package com.leninkumar.vendingmachine.exceptions;

public class InsufficientAmountException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3181364666004426614L;

	public InsufficientAmountException(String msg) {
		super(msg);
	}
}
