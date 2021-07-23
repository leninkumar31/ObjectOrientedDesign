package com.chess.models;

import com.chess.models.piece.Piece;
import com.chess.models.piece.PieceType;

public class Board {
	Spot[][] board;

	Board() {
		this.board = new Spot[8][8];
		initialize();
	}

	private void initialize() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					board[i][j] = new Spot(i, j, true);
				} else {
					board[i][j] = new Spot(i, j, false);
				}
			}
		}
		// Initializing Pawns
		for (int i = 0; i < 8; i++) {
			board[1][i].piece = Piece.getPiece(PieceType.PAWN, false, this);
			board[6][i].piece = Piece.getPiece(PieceType.PAWN, true, this);
		}
		// Initializing Rooks
		board[0][0].piece = Piece.getPiece(PieceType.ROOK, false, this);
		board[0][7].piece = Piece.getPiece(PieceType.ROOK, false, this);
		board[7][0].piece = Piece.getPiece(PieceType.ROOK, true, this);
		board[7][7].piece = Piece.getPiece(PieceType.ROOK, true, this);
		// Initializing Knight
		board[0][1].piece = Piece.getPiece(PieceType.KNIGHT, false, this);
		board[0][6].piece = Piece.getPiece(PieceType.KNIGHT, false, this);
		board[7][1].piece = Piece.getPiece(PieceType.KNIGHT, true, this);
		board[7][6].piece = Piece.getPiece(PieceType.KNIGHT, true, this);
		// Initializing Bishop
		board[0][2].piece = Piece.getPiece(PieceType.BISHOP, false, this);
		board[0][5].piece = Piece.getPiece(PieceType.BISHOP, false, this);
		board[7][2].piece = Piece.getPiece(PieceType.BISHOP, true, this);
		board[7][5].piece = Piece.getPiece(PieceType.BISHOP, true, this);
		// Initializing Queen
		board[0][3].piece = Piece.getPiece(PieceType.QUEEN, false, this);
		board[7][3].piece = Piece.getPiece(PieceType.QUEEN, true, this);
		// Initializing King
		board[0][4].piece = Piece.getPiece(PieceType.KING, false, this);
		board[7][4].piece = Piece.getPiece(PieceType.KING, true, this);
	}

	public Spot[][] getBoard() {
		return board;
	}

	public Spot getSpot(int x, int y) {
		return board[x][y];
	}
}
