package com.leninkumar.connect4.player;

import java.util.Scanner;

import com.leninkumar.connect4.game.Board;
import com.leninkumar.connect4.game.Symbol;

import lombok.Getter;

@Getter
public class HumanPlayer extends Player {
	private String userName;
	private Scanner sc = new Scanner(System.in);

	public HumanPlayer(String user, Symbol symbol) {
		super(symbol);
		this.userName = user;
	}

	@Override
	public int makeMove(Board gameBoard) {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int col = sc.nextInt();
			if (col < 0 || col >= gameBoard.getNumCols()) {
				System.out.println("Invalid input. Enter again between 0 and " + (gameBoard.getNumCols() - 1));
			} else {
				return col;
			}
		}
	}

}
