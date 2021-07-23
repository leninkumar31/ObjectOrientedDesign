package com.chess.models;

import java.util.ArrayList;
import java.util.List;

import com.chess.models.account.Player;

public class ChessGame {
	private List<Move> moveLog;
	private Player[] players;
	private GameStatus gameStatus;
	private Board gameBoard;
	private int currTurn;

	public ChessGame(Player player1, Player player2) {
		this.moveLog = new ArrayList<>();
		this.players = new Player[] { player1, player2 };
		this.gameBoard = new Board();
		this.currTurn = 0;
		this.gameStatus = GameStatus.INPROGRESS;
	}
	
	public boolean makeMove(Spot from, Spot to) {
		// CHeck whether the piece exists in the from position or not
		// check the piece whether it belongs to current player or not
		// Call piece canMove method
		// if it returns true, make move
		// add it to moveLog
		return false;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public void setCurrTurn(int currTurn) {
		this.currTurn = currTurn;
	}

	public void addMove(Move move) {
		this.moveLog.add(move);
	}

	public List<Move> getMoveLog() {
		return moveLog;
	}

	public Player[] getPlayers() {
		return players;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public Board getGameBoard() {
		return gameBoard;
	}

	public int getCurrTurn() {
		return currTurn;
	}

}
