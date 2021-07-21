package com.deckofcards.models.hand;

import java.util.ArrayList;
import java.util.List;

import com.deckofcards.models.card.Card;

public class Hand<T extends Card> {
	private List<T> cards;

	public Hand() {
		this.cards = new ArrayList<>();
	}
	
	public List<T> getCards(){
		return this.cards;
	}
	
	public void addCard(T card) {
		this.cards.add(card);
	}
	
	public int score() {
		int sum = 0;
		for(T card : this.cards) {
			sum += card.getValue();
		}
		return sum;
	}
}
