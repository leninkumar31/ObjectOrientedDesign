package com.chess.models.piece;

import com.chess.models.Board;
import com.chess.models.Spot;

public class Pawn extends Piece {

	Pawn(boolean isWhite, PieceType type, Board board) {
		super(isWhite, type, board);
	}

	@Override
	public boolean canMove(Spot from, Spot to) {
		if (Math.abs(to.getX() - from.getX()) > 2 || Math.abs(to.getX() - from.getX()) == 0
				|| (from.isWhite() && from.getX() < to.getX()) || (!from.isWhite() && from.getX() > to.getX())) {
			return false;
		}
		if (to.getPiece() != null && to.isWhite() == from.isWhite()) {
			return false;
		}
		if (Math.abs(to.getX() - from.getX()) == 2) {
			if (from.getY() != to.getY() || (from.getPiece().isWhite() && from.getY() != 6)
					|| (!from.getPiece().isWhite() && from.getY() != 1)) {
				return false;
			}
			return true;
		}
		if (Math.abs(to.getX() - from.getX()) == 1) {
			if (Math.abs(from.getY() - to.getY()) > 1) {
				return false;
			}
			if(Math.abs(from.getY() - to.getY()) == 0) {
				return true;
			}
			if(Math.abs(from.getY() - to.getY()) == 1) {
				return to.getPiece() != null && to.getPiece().isWhite() != from.getPiece().isWhite();
			}
		}
		return true;
	}
}
