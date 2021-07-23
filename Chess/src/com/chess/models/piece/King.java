package com.chess.models.piece;

import com.chess.models.Board;
import com.chess.models.Spot;

public class King extends Piece {

	King(boolean isWhite, PieceType type, Board board) {
		super(isWhite, type, board);
	}

	@Override
	public boolean canMove(Spot from, Spot to) {
		int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
		for (int i = 0; i < 8; i++) {
			int nx = from.getX() + x[i];
			int ny = from.getY() + y[i];
			if (nx >= 0 && ny >= 0 && nx < 8 && ny < 8) {
				if (to.getX() == nx && to.getY() == ny) {
					return to.getPiece() == null || (to.getPiece() != null && to.isWhite() != from.isWhite());
				}
			}
		}
		return false;
	}

}
