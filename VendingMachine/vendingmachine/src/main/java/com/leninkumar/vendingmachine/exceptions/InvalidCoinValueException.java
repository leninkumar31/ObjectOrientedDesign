package com.leninkumar.vendingmachine.exceptions;

public class InvalidCoinValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8083243969098899997L;

	public InvalidCoinValueException(String msg) {
		super(msg);
	}
}
