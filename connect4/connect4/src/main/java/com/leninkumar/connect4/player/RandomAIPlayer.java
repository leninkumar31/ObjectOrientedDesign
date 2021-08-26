package com.leninkumar.connect4.player;

import com.leninkumar.connect4.game.Board;
import com.leninkumar.connect4.game.Symbol;

public class RandomAIPlayer extends Player {

	public RandomAIPlayer(Symbol symbol) {
		super(symbol);
	}

	@Override
	public int makeMove(Board gameBoard) {
		return (int) (Math.random() * gameBoard.getNumCols());
	}

}
