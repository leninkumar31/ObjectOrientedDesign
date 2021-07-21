package com.deckofcards.models.card;

public enum Rank {
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	KING(12),
	QUEEN(13);
	
	private final int val;
	
	Rank(int val){
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
