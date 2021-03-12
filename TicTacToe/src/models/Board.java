package models;

import exceptions.IncorrectInputException;
import exceptions.PositionAlreadySetException;

public class Board {
	Symbol[][] board;
	private int rowLen, columnLen;

	public Board(int rowLen, int colLen) {
		board = new Symbol[rowLen][colLen];
		this.rowLen = rowLen;
		this.columnLen = colLen;
		for (int i = 0; i < this.rowLen; i++) {
			for (int j = 0; j < this.columnLen; j++) {
				board[i][j] = Symbol.BLANK;
			}
		}
	}

	public int getRowLen() {
		return this.rowLen;
	}

	public int getColumnLen() {
		return this.columnLen;
	}

	public void printBoard() {
		for (int i = 0; i <= this.rowLen; i++) {
			for (int j = 0; j < this.columnLen; j++) {
				System.out.print("--");
			}
			System.out.println();
			if (i == this.columnLen) {
				break;
			}
			for (int j = 0; j <= this.columnLen; j++) {
				System.out.print("|");
				if (j == this.columnLen)
					break;
				System.out.print(this.board[i][j].toString());
			}
			System.out.println();
		}
	}

	public void setBoardPosition(Position pos, Symbol val) throws Exception {
		if (!pos.isPositionValid(this.getRowLen(), this.getColumnLen())) {
			throw new IncorrectInputException("Invalid input");
		}
		if (this.board[pos.getX()][pos.getY()] != Symbol.BLANK)
			throw new PositionAlreadySetException("Position is already Set");
		this.board[pos.getX()][pos.getY()] = val;
	}

	public Symbol getBoardPosition(int x, int y) {
		return this.board[x][y];
	}

}
