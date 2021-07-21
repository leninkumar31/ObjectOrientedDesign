package com.deckofcards.models.account;

import com.deckofcards.models.card.Card;
import com.deckofcards.models.card.Deck;

public class Dealer<T extends Card> extends Account<T> {
	private Deck<T> deckOfCards;

	public Dealer(Deck<T> deck) {
		this.deckOfCards = deck;
		this.deckOfCards.shuffle();
	}

	public T drawCard() {
		return this.deckOfCards.dealCard();
	}

}
