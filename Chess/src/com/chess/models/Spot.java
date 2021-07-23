package com.chess.models;

import com.chess.models.piece.Piece;

public class Spot {
	int x, y;
	boolean isWhite;
	Piece piece;

	Spot(int x, int y, boolean isWhite) {
		this.x = x;
		this.y = y;
		this.isWhite = isWhite;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

}
