package com.leninkumar.connect4.player;

import com.leninkumar.connect4.exception.ColumnIsNotEmptyException;
import com.leninkumar.connect4.game.Board;
import com.leninkumar.connect4.game.Symbol;

public class RudimentaryAIPlayer extends Player {

	RudimentaryAIPlayer(Symbol symbol) {
		super(symbol);
	}

	@Override
	public int makeMove(Board gameBoard) {
		Symbol[][] grid = gameBoard.getGrid();
		// check AI can fill 4 horizontal positions
		for (int i = 0; i < gameBoard.getNumRows(); i++) {
			for (int j = 0; j < gameBoard.getNumCols() - 3; j++) {
				if (grid[i][j] == this.symbol) {
					if (grid[i][j] == grid[i][j + 1] && grid[i][j + 1] == grid[i][j + 2]) {
						try {
							int row = gameBoard.findEmptyPosFromBottomInCol(j + 3);
							if (row == i) {
								return j + 3;
							}
						} catch (ColumnIsNotEmptyException e) {
							continue;
						}

					}
				}
			}
		}
		// check AI can fill 4 vertical positions
		for (int i = 0; i < gameBoard.getNumCols(); i++) {
			for (int j = gameBoard.getNumRows() - 1; j >= 3; j--) {
				if (grid[j][i] == this.symbol) {
					if (grid[j][i] == grid[j - 1][i] && grid[j - 1][i] == grid[j - 2][i]) {
						if (grid[j - 3][i] == Symbol.BLANK) {
							return i;
						}
					}
				}
			}
		}
		// check AI can prevent opponents 4 horizontal positions
		for (int i = 0; i < gameBoard.getNumRows(); i++) {
			for (int j = 0; j < gameBoard.getNumCols() - 3; j++) {
				if (grid[i][j] != this.symbol && grid[i][j] != Symbol.BLANK) {
					if (grid[i][j] == grid[i][j + 1] && grid[i][j + 1] == grid[i][j + 2]) {
						try {
							int row = gameBoard.findEmptyPosFromBottomInCol(j + 3);
							if (row == i) {
								return j + 3;
							}
						} catch (ColumnIsNotEmptyException e) {
							continue;
						}

					}
				}
			}
		}
		// check AI can prevent opponents 4 vertical positions
		for (int i = 0; i < gameBoard.getNumCols(); i++) {
			for (int j = gameBoard.getNumRows() - 1; j >= 3; j--) {
				if (grid[j][i] != this.symbol && grid[j][i] != Symbol.BLANK) {
					if (grid[j][i] == grid[j - 1][i] && grid[j - 1][i] == grid[j - 2][i]) {
						if (grid[j - 3][i] == Symbol.BLANK) {
							return i;
						}
					}
				}
			}
		}
		return (int) (Math.random() * gameBoard.getNumCols());
	}

}