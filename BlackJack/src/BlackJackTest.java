import java.util.Scanner;

import com.deckofcards.models.account.Dealer;
import com.deckofcards.models.account.Player;
import com.deckofcards.models.blackjack.Action;
import com.deckofcards.models.blackjack.BlackJackCard;
import com.deckofcards.models.blackjack.BlackJackGame;
import com.deckofcards.models.card.Deck;
import com.deckofcards.models.exceptions.InsufficientCashException;
import com.deckofcards.models.exceptions.InvalidActionException;
import com.deckofcards.models.game.CardGame;
import com.deckofcards.models.game.GameState;
import com.deckofcards.models.game.GameType;

public class BlackJackTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Deck<BlackJackCard> deck = new Deck<>();
		deck.setDeck(BlackJackCard.getDeckOfBlackJackCards());
		Player<BlackJackCard> player = new Player<>();
		player.setTotalCash(1000);
		Dealer<BlackJackCard> dealer = new Dealer<>(deck);
		dealer.setTotalCash(100000);
		BlackJackGame game = (BlackJackGame) CardGame.createCardGame(GameType.BLACKJACK);
		game.setPlayer(player);
		game.setDealer(dealer);
		game.createHands();
		while (true) {
			System.out.println(game.getPlayerScore() + "," + game.getDealerScore());
			String input = sc.next();
			Action action;
			try {
				action = Action.getAction(input);
			} catch (InvalidActionException e) {
				System.out.println("Input is not among one of the action. Please enter again");
				continue;
			}
			GameState currState = GameState.INPROGRESS;
			switch (action) {
			case HIT:
				currState = game.hit();
				break;
			case STAND:
				currState = game.stand();
				break;
			case DOUBLE:
				try {
					currState = game.doubleDown();
				} catch (InsufficientCashException e) {
					System.out.println("Available cash is insufficient. Please enter your action again.");
					continue;
				}
				break;
			}
			if (currState == GameState.LOST) {
				System.out.println("Player has lost the game");
				break;
			}
			if (currState == GameState.WON) {
				System.out.println("Player has won the game");
				break;
			}
		}
		sc.close();
	}

}
