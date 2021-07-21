package com.deckofcards.models.account;

import com.deckofcards.models.card.Card;
import com.deckofcards.models.exceptions.InsufficientCashException;

public class Player<T extends Card> extends Account<T> {
	private int bet;
	
	public Player() {
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) throws InsufficientCashException {
		if(bet > this.getTotalCash()) {
			throw new InsufficientCashException("Bet is more than total cash: "+bet);
		}
		this.bet = bet;
	}

	// View open games
	// Join an open game
	// Place a bet
	// HIT
	// Stand
	// Double
}
