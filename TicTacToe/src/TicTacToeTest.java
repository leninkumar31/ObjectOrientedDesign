import java.util.Scanner;

import exceptions.IncorrectInputException;
import exceptions.PositionAlreadySetException;
import impl.TicTacToe;
import models.GameState;
import models.Position;
import models.Symbol;

public class TicTacToeTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		System.out.println("Initial board position");
		game.getBoard().printBoard();
		int currPlayer = 1;
		while (true) {
			System.out.printf(
					"Player %d, its your move. You have to give input the position in the following format: x y\n",
					currPlayer);
			int x = sc.nextInt();
			int y = sc.nextInt();
			try {
				Position pos = new Position(x - 1, y - 1);
				game.makeMove(pos, getPlayerSymbol(currPlayer));
			} catch (IncorrectInputException | PositionAlreadySetException ex) {
				System.out.println("Something is wrong with given Cell Position. " + ex.getMessage());
				continue;
			} catch (Exception e) {
				System.out.println("Some Other problem. Please try again." + e.getStackTrace());
				continue;
			}
			game.getBoard().printBoard();
			if (game.isGameOver()) {
				GameState state = game.getGameState();
				if (state == GameState.DRAW) {
					System.out.println("Game is Over. It is a Draw!!!");
				} else {
					System.out.printf("Game is Over. Player %d won :)", currPlayer);
				}
				break;
			}
			currPlayer = currPlayer % 2 + 1;
		}
		sc.close();
	}

	private static Symbol getPlayerSymbol(int player) {
		if (player == 1) {
			return Symbol.CROSS;
		}
		return Symbol.CIRCLE;
	}
}
