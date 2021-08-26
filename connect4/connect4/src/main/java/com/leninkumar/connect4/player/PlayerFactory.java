package com.leninkumar.connect4.player;

import com.leninkumar.connect4.game.Symbol;

public class PlayerFactory {

	public static Player getHumanPlayer(String name, Symbol symbol) {
		return new HumanPlayer(name, symbol);
	}

	public static Player getRandomAIPlayer(Symbol symbol) {
		return new RandomAIPlayer(symbol);
	}
}
