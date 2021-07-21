package com.deckofcards.models.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.deckofcards.models.card.Card;
import com.deckofcards.models.card.Rank;
import com.deckofcards.models.card.Suit;

public class BlackJackCard extends Card {

	public BlackJackCard(Suit suit, Rank rank) {
		super(suit, rank);
	}

	@Override
	public int getValue() {
		if (isAce()) {
			return 1;
		}
		if (this.getRank().getVal() >= 11 && this.getRank().getVal() <= 13) {
			return 10;
		}
		return this.getRank().getVal();
	}

	public int minValue() {
		if (isAce()) {
			return 1;
		}
		return this.getRank().getVal();
	}

	public int maxValue() {
		if (isAce()) {
			return 11;
		}
		return this.getRank().getVal();
	}

	public boolean isFaceCard() {
		return this.getRank().getVal() >= 11 && this.getRank().getVal() <= 13;
	}

	public boolean isAce() {
		return this.getRank() == Rank.ACE;
	}

	public static List<BlackJackCard> getDeckOfBlackJackCards() {
		List<BlackJackCard> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new BlackJackCard(suit, rank));
			}
		}
		return deck;
	}
}
