package com.chess.models.exceptions;

public class InvalidMoveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2993345227723373670L;

	InvalidMoveException(String msg) {
		super(msg);
	}
}
