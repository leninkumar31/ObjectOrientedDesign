package impl;

import models.Board;
import models.Game;
import models.GameState;
import models.Position;
import models.Symbol;

public class TicTacToe implements Game {
	Board ticTacToe;

	public TicTacToe() {
		this.ticTacToe = new Board(3, 3);
	}

	public Board getBoard() {
		return this.ticTacToe;
	}

	public void makeMove(Position pos, Symbol symbol) throws Exception {
		this.ticTacToe.setBoardPosition(pos, symbol);
	}

	public Boolean isGameOver() {
		GameState state = this.getGameState();
		return state != GameState.INPROGRESS ? true : false;
	}

	public Symbol getBoardPosition(int x, int y) {
		return this.ticTacToe.getBoardPosition(x, y);
	}

	public GameState getGameState() {
		if (this.getBoardPosition(1, 1) != Symbol.BLANK) {
			if (this.getBoardPosition(0, 0) == this.getBoardPosition(1, 1)
					&& this.getBoardPosition(2, 2) == this.getBoardPosition(1, 1)) {
				return GameState.WIN;
			}
			if (this.getBoardPosition(0, 2) == this.getBoardPosition(1, 1)
					&& this.getBoardPosition(2, 0) == this.getBoardPosition(1, 1)) {
				return GameState.WIN;
			}
		}

		for (int i = 0; i < this.ticTacToe.getRowLen(); i++) {
			if (this.getBoardPosition(i, 0) != Symbol.BLANK && this.getBoardPosition(i, 1) != Symbol.BLANK
					&& this.getBoardPosition(i, 2) != Symbol.BLANK) {
				if (this.getBoardPosition(i, 0) == this.getBoardPosition(i, 1)
						&& this.getBoardPosition(i, 1) == this.getBoardPosition(i, 2)) {
					return GameState.WIN;
				}
			}
		}

		for (int j = 0; j < this.ticTacToe.getColumnLen(); j++) {
			if (this.getBoardPosition(0, j) != Symbol.BLANK && this.getBoardPosition(1, j) != Symbol.BLANK
					&& this.getBoardPosition(2, j) != Symbol.BLANK) {
				if (this.getBoardPosition(0, j) == this.getBoardPosition(1, j)
						&& this.getBoardPosition(1, j) == this.getBoardPosition(2, j)) {
					return GameState.WIN;
				}
			}
		}
		int blankCellCnt = 0;
		for (int i = 0; i < this.ticTacToe.getRowLen(); i++) {
			for (int j = 0; j < this.ticTacToe.getColumnLen(); j++) {
				if (this.getBoardPosition(i, j) == Symbol.BLANK) {
					blankCellCnt++;
				}
			}
		}

		return blankCellCnt > 0 ? GameState.INPROGRESS : GameState.DRAW;
	}
}
