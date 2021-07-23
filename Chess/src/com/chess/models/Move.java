package com.chess.models;

import com.chess.models.account.Player;
import com.chess.models.piece.Piece;

public class Move {
	private Spot from, to;
	private Piece pieceMoved, pieceDied;
	private Player player;

	public Move(Player player, Spot from, Spot to) {
		this.player = player;
		this.from = from;
		this.to = to;
	}

	public void setPieceMoved(Piece pieceMoved) {
		this.pieceMoved = pieceMoved;
	}

	public void setPieceDied(Piece pieceDied) {
		this.pieceDied = pieceDied;
	}

	public Spot getFrom() {
		return from;
	}

	public Spot getTo() {
		return to;
	}

	public Piece getPieceMoved() {
		return pieceMoved;
	}

	public Piece getPieceDied() {
		return pieceDied;
	}

	public Player getPlayer() {
		return player;
	}

}
