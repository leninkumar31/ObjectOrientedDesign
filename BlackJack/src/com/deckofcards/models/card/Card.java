package com.deckofcards.models.card;

public abstract class Card {
	private boolean isAvailable;
	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
		this.isAvailable = true;
	}

	public Suit getSuit() {
		return suit;
	}
	
	public Rank getRank() {
		return rank;
	}

	public abstract int getValue();

	public boolean isAvailable() {
		return isAvailable;
	}
	
	public void markUnAvailable() {
		this.isAvailable = false;
	}
	
	public void markAvailable() {
		this.isAvailable = true;
	}

	@Override
	public String toString() {
		return suit + "-" + rank;
	}

}
