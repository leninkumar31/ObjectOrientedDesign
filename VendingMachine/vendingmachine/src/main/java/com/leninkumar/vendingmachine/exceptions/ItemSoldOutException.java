package com.leninkumar.vendingmachine.exceptions;

public class ItemSoldOutException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6056435403427357638L;

	public ItemSoldOutException(String msg) {
		super(msg);
	}
}
