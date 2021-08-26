package com.leninkumar.connect4;

import com.leninkumar.connect4.game.Board;
import com.leninkumar.connect4.game.GameManager;
import com.leninkumar.connect4.game.GameState;
import com.leninkumar.connect4.game.GameStatus;
import com.leninkumar.connect4.game.Symbol;
import com.leninkumar.connect4.player.Player;
import com.leninkumar.connect4.player.PlayerFactory;

public class Connect4Test {

	public static void main(String[] args) {
		Board board = new Board(6, 7);
		Player player1 = PlayerFactory.getHumanPlayer("lenin", Symbol.BLUE);
		Player player2 = PlayerFactory.getRudimentaryAIPlayer(Symbol.RED);
		GameState gameState = new GameState(player1, player2, board);
		GameManager manager = new GameManager(gameState);
		while (true) {
			manager.printBoard();
			System.out.println("==========================");
			manager.makeMove();
			GameStatus status = manager.isGameOver();
			switch (status) {
			case WIN:
				System.out.println("Game is over. Won by Player:" + manager.getCurrPlayer());
				manager.printBoard();
				return;
			case DRAW:
				System.out.println("Game is draw.");
				manager.printBoard();
				return;
			default:
				manager.updatePlayer();
				break;
			}
		}
	}
}
