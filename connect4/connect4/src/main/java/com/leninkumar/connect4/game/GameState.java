package com.leninkumar.connect4.game;

import java.util.UUID;

import com.leninkumar.connect4.player.Player;

import lombok.Getter;

@Getter
public class GameState {
	private String gameId;
	private Player[] players;
	private int currPlayer;
	private Board board;
	private GameStatus gameStatus;
	private Player winningPlayer;

	public GameState(Player player1, Player player2, Board board) {
		this.gameId = UUID.randomUUID().toString();
		this.players = new Player[2];
		this.players[0] = player1;
		this.players[1] = player2;
		this.currPlayer = 0;
		this.board = board;
		this.gameStatus = GameStatus.INPROGRESS;
	}

	public void changeCurrPlayer() {
		this.currPlayer = (currPlayer + 1) % 2;
	}

	public int getCurrentPlayerId() {
		return this.currPlayer;
	}

	public Player getCurrentPlayer() {
		return this.players[this.currPlayer];
	}

	public void setGameStatus(GameStatus status) {
		this.gameStatus = status;
	}

	public void printBoard() {
		this.board.printBoard();
	}

	public void closeGame(GameStatus status) {
		if (status == GameStatus.WIN) {
			this.winningPlayer = this.getCurrentPlayer();
		}
		this.gameStatus = GameStatus.COMPLETED;
	}
}
