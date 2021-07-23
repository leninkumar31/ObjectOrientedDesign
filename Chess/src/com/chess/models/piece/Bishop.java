package com.chess.models.piece;

import com.chess.models.Board;
import com.chess.models.Spot;

public class Bishop extends Piece {

	Bishop(boolean isWhite, PieceType type, Board board) {
		super(isWhite, type, board);
	}

	@Override
	public boolean canMove(Spot from, Spot to) {
		if ((from.getX() + from.getY() != to.getX() + to.getY())
				&& (Math.abs(from.getX() - to.getX()) != Math.abs(from.getY() - to.getY()))) {
			return false;
		}
		if (from.getX() == to.getX() && from.getY() == to.getY()) {
			return false;
		}
		// Diagonal
		if (Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY())) {
			int minX = Math.min(from.getX(), to.getX()), maxX = Math.max(from.getX(), to.getX());
			minX++;
			maxX--;
			int minY = Math.min(from.getY(), to.getY()), maxY = Math.max(from.getY(), to.getY());
			minY++;
			maxY--;
			while (minX <= maxX && minY <= maxY) {
				if (this.getBoard().getSpot(minX, minY).getPiece() != null) {
					return false;
				}
				minX++;
				minY++;
			}
			return to.getPiece() == null || (to.getPiece() != null && from.isWhite() != to.isWhite());
		}
		// Anti-Diagonal
		if (from.getX() + from.getY() == to.getX() + to.getY()) {
			int minX, minY, maxX;
			if (from.getX() < to.getX()) {
				minX = from.getX();
				maxX = to.getX();
				minY = from.getY();
			} else {
				minX = to.getX();
				maxX = from.getX();
				minY = to.getY();
			}
			minX++;
			minY--;
			maxX--;
			while (minX <= maxX && minY >= 0) {
				if (this.getBoard().getSpot(minX, minY).getPiece() != null) {
					return false;
				}
				minX++;
				minY--;
			}
			return to.getPiece() == null || (to.getPiece() != null && from.isWhite() != to.isWhite());
		}
		return false;
	}

}
