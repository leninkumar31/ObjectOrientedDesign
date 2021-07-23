package com.chess.models.account;

public abstract class Player {
	private PlayerType type;
	private boolean isWhite;

	public static Player getPlayer(PlayerType type) {
		switch (type) {
		case HUMAN:
			return new HumanPlayer(type);
		case COMPUTER:
			return new ComputerPlayer(type);
		}
		return null;
	}

	Player(PlayerType type) {
		this.type = type;
	}

	public PlayerType getType() {
		return type;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

}
