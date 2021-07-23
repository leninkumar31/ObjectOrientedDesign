package com.chess.models.exceptions;

public class PieceNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2993345227723373670L;

	public PieceNotFoundException(String msg) {
		super(msg);
	}
}
