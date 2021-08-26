package com.leninkumar.connect4.game;

import com.leninkumar.connect4.player.Player;

import lombok.Getter;

@Getter
public class Move {
	Player player;
	Symbol symbol;
	int row, col;

	public Move(Player player, int row, int col) {
		this.player = player;
		this.symbol = player.getSymbol();
		this.row = row;
		this.col = col;
	}
}
