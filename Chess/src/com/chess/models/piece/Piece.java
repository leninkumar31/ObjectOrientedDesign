package com.chess.models.piece;

import com.chess.models.Board;
import com.chess.models.Spot;

public abstract class Piece {
	private boolean isWhite;
	private boolean isAlive;
	private final PieceType type;
	private Board board;

	Piece(boolean isWhite, PieceType type, Board board) {
		this.isWhite = isWhite;
		this.isAlive = true;
		this.type = type;
		this.board = board;
	}

	public static Piece getPiece(PieceType type, boolean isWhite, Board board) {
		switch (type) {
		case PAWN:
			return new Pawn(isWhite, type, board);
		case ROOK:
			return new Rook(isWhite, type, board);
		case KING:
			return new King(isWhite, type, board);
		case QUEEN:
			return new Queen(isWhite, type, board);
		case BISHOP:
			return new Bishop(isWhite, type, board);
		case KNIGHT:
			return new Knight(isWhite, type, board);
		}
		return null;
	}

	public Board getBoard() {
		return board;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public PieceType getType() {
		return type;
	}

	public abstract boolean canMove(Spot from, Spot to);

}
