package com.deckofcards.models.exceptions;

public class InsufficientCashException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 173655127018382735L;

	public InsufficientCashException(String message) {
		super(message);
	}
}
