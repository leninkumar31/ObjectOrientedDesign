package com.leninkumar.connect4.game;

import com.leninkumar.connect4.exception.ColumnIsNotEmptyException;
import com.leninkumar.connect4.exception.InvalidInputException;
import com.leninkumar.connect4.exception.PositionIsAlreadySetException;

import lombok.Getter;

@Getter
public class Board {
	private Symbol[][] grid;
	private int numRows, numCols;

	public Board(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.grid = new Symbol[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				this.grid[i][j] = Symbol.BLANK;
			}
		}
	}

	public void setBoardPosition(int x, int y, Symbol symbol)
			throws InvalidInputException, PositionIsAlreadySetException {
		if (x < 0 || y < 0 || x >= numRows || y >= numCols) {
			throw new InvalidInputException("Input is invalid");
		}
		if (this.grid[x][y] != Symbol.BLANK) {
			throw new PositionIsAlreadySetException("Given position is already set");
		}
		this.grid[x][y] = symbol;
	}

	public Symbol getBoardPosition(int x, int y) throws InvalidInputException {
		if (x < 0 || y < 0 || x >= numRows || y >= numCols) {
			throw new InvalidInputException("Input is invalid");
		}
		return this.grid[x][y];
	}

	public int findEmptyPosFromBottomInCol(int col) throws ColumnIsNotEmptyException {
		for (int i = numRows - 1; i >= 0; i--) {
			if (grid[i][col] == Symbol.BLANK) {
				return i;
			}
		}
		throw new ColumnIsNotEmptyException("Given column doesn't have an empty position");
	}

	public void printBoard() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				System.out.print(grid[i][j].toString());
			}
			System.out.println();
		}
	}
}
