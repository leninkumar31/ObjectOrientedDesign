package com.chess.models.piece;

import com.chess.models.Board;
import com.chess.models.Spot;

public class Rook extends Piece {

	Rook(boolean isWhite, PieceType type, Board board) {
		super(isWhite, type, board);
	}

	@Override
	public boolean canMove(Spot from, Spot to) {
		if (to.getX() != from.getX() && to.getY() != from.getY()) {
			return false;
		}
		if (from.getX() == to.getX() && from.getY() == to.getY()) {
			return false;
		}
		if (to.getX() == from.getX()) {
			for (int i = Math.min(to.getY(), from.getY()) + 1; i < Math.max(to.getY(), from.getY()); i++) {
				if (this.getBoard().getSpot(to.getX(), i).getPiece() != null) {
					return false;
				}
			}
		} else {
			for (int i = Math.min(to.getX(), from.getX()) + 1; i < Math.max(to.getX(), from.getX()); i++) {
				if (this.getBoard().getSpot(i, to.getY()).getPiece() != null) {
					return false;
				}
			}
		}
		return to.getPiece() == null || (to.getPiece() != null && to.isWhite() != from.isWhite());
	}

}
