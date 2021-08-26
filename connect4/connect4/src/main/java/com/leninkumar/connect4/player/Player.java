package com.leninkumar.connect4.player;

import com.leninkumar.connect4.game.Board;
import com.leninkumar.connect4.game.Symbol;

import lombok.Getter;

@Getter
public abstract class Player {
	Symbol symbol;

	Player(Symbol symbol) {
		this.symbol = symbol;
	}

	public abstract int makeMove(Board gameBoard);
}
