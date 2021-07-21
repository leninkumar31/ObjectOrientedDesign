package com.deckofcards.models.blackjack;

import com.deckofcards.models.account.Dealer;
import com.deckofcards.models.account.Player;
import com.deckofcards.models.exceptions.InsufficientCashException;
import com.deckofcards.models.game.CardGame;
import com.deckofcards.models.game.GameState;

public class BlackJackGame extends CardGame {
	private Player<BlackJackCard> player;
	private Dealer<BlackJackCard> dealer;

	public BlackJackGame() {

	}

	public BlackJackGame(Player<BlackJackCard> player, Dealer<BlackJackCard> dealer) {
		this.player = player;
		this.dealer = dealer;
	}

	public void createHands() {
		BlackJackHand playerHand = new BlackJackHand();
		playerHand.addCard(this.dealer.drawCard());
		playerHand.addCard(this.dealer.drawCard());
		this.player.setHand(playerHand);
		BlackJackHand dealerHand = new BlackJackHand();
		dealerHand.addCard(this.dealer.drawCard());
		dealerHand.addCard(this.dealer.drawCard());
		this.dealer.setHand(dealerHand);
	}

	public int getPlayerScore() {
		return this.player.getHand().score();
	}
	
	public int getDealerScore() {
		return this.dealer.getHand().score();
	}

	public GameState hit() {
		this.player.getHand().addCard(this.dealer.drawCard());
		if (getPlayerScore() > 21) {
			return GameState.LOST;
		}
		return GameState.INPROGRESS;
	}

	public GameState stand() {
		int playerScore = this.player.getHand().score();
		int dealerScore = this.dealer.getHand().score();
		if (dealerScore > playerScore) {
			return GameState.LOST;
		}
		while (true) {
			this.dealer.getHand().addCard(this.dealer.drawCard());
			if (this.dealer.getHand().score() > 21) {
				return GameState.WON;
			}
			if (dealerScore > playerScore) {
				return GameState.LOST;
			}
			if (dealerScore > 17) {
				break;
			}
		}
		return GameState.WON;
	}

	public GameState doubleDown() throws InsufficientCashException {
		this.player.setBet(2 * this.player.getBet());
		return this.hit();
	}

	public Player<BlackJackCard> getPlayer() {
		return player;
	}

	public void setPlayer(Player<BlackJackCard> player) {
		this.player = player;
	}

	public Dealer<BlackJackCard> getDealer() {
		return dealer;
	}

	public void setDealer(Dealer<BlackJackCard> dealer) {
		this.dealer = dealer;
	}

}
