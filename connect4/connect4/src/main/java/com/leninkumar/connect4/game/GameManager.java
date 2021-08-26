package com.leninkumar.connect4.game;

import com.leninkumar.connect4.exception.ColumnIsNotEmptyException;
import com.leninkumar.connect4.exception.InvalidInputException;
import com.leninkumar.connect4.exception.PositionIsAlreadySetException;
import com.leninkumar.connect4.player.Player;

public class GameManager {
	private GameState gameState;

	public GameManager(GameState gameState) {
		this.gameState = gameState;
	}

	public void makeMove() {
		int row, col;
		while (true) {
			Player player = this.gameState.getCurrentPlayer();
			System.out.println("Player " + this.gameState.getCurrentPlayerId() + " move:");
			col = player.makeMove(gameState.getBoard());
			try {
				row = this.gameState.getBoard().findEmptyPosFromBottomInCol(col);
				this.gameState.getBoard().setBoardPosition(row, col, player.getSymbol());
				this.gameState.addMove(new Move(player, row, col));
				return;
			} catch (ColumnIsNotEmptyException | InvalidInputException | PositionIsAlreadySetException e) {
				System.out.println("Invalid Input. Please enter valid one");
			}
		}
	}

	public GameStatus isGameOver() {
		Symbol[][] board = this.gameState.getBoard().getGrid();
		// check the rows
		for (int i = 0; i < this.gameState.getBoard().getNumRows(); i++) {
			for (int j = 0; j < this.gameState.getBoard().getNumCols() - 3; j++) {
				if (board[i][j] == this.gameState.getCurrentPlayer().getSymbol()) {
					if (board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2]
							&& board[i][j + 2] == board[i][j + 3]) {
						this.gameState.closeGame(GameStatus.COMPLETED);
						return GameStatus.WIN;
					}
				}
			}
		}
		// check the columns
		for (int i = 0; i < this.gameState.getBoard().getNumCols(); i++) {
			for (int j = 0; j < this.gameState.getBoard().getNumRows() - 3; j++) {
				if (board[j][i] == this.gameState.getCurrentPlayer().getSymbol()) {
					if (board[j][i] == board[j + 1][i] && board[j + 1][i] == board[j + 2][i]
							&& board[j + 2][i] == board[j + 3][i]) {
						this.gameState.closeGame(GameStatus.COMPLETED);
						return GameStatus.WIN;
					}
				}
			}
		}
		// check upward diagonal
		for (int i = 3; i < this.gameState.getBoard().getNumRows(); i++) {
			for (int j = 0; j < this.gameState.getBoard().getNumRows() - 3; j++) {
				if (board[i][j] == this.gameState.getCurrentPlayer().getSymbol()) {
					if (board[i][j] == board[i - 1][j + 1] && board[i - 1][j + 1] == board[i - 2][j + 2]
							&& board[i - 2][j + 2] == board[i - 3][j + 3]) {
						this.gameState.closeGame(GameStatus.COMPLETED);
						return GameStatus.WIN;
					}
				}
			}
		}
		// check downward diagonal
		for (int i = 0; i < this.gameState.getBoard().getNumRows() - 3; i++) {
			for (int j = 0; j < this.gameState.getBoard().getNumRows() - 3; j++) {
				if (board[i][j] == this.gameState.getCurrentPlayer().getSymbol()) {
					if (board[i][j] == board[i + 1][j + 1] && board[i + 1][j + 1] == board[i + 2][j + 2]
							&& board[i + 2][j + 2] == board[i + 3][j + 3]) {
						this.gameState.closeGame(GameStatus.COMPLETED);
						return GameStatus.WIN;
					}
				}
			}
		}
		// check for draw
		boolean isDraw = true;
		for (int i = 0; i < this.gameState.getBoard().getNumRows(); i++) {
			for (int j = 0; j < this.gameState.getBoard().getNumCols() - 3; j++) {
				if (board[i][j] == Symbol.BLANK) {
					isDraw = false;
					break;
				}
			}
		}
		if (isDraw) {
			this.gameState.closeGame(GameStatus.COMPLETED);
			return GameStatus.DRAW;
		}
		return GameStatus.INPROGRESS;
	}

	public void printBoard() {
		this.gameState.printBoard();
	}

	public int getCurrPlayer() {
		return this.gameState.getCurrentPlayerId();
	}

	public void updatePlayer() {
		this.gameState.changeCurrPlayer();
	}
}
