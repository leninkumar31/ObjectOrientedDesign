package com.deckofcards.models.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck<T extends Card> {
	private List<T> deck;
	private int dealtIndex = 0;

	public Deck() {
		deck = new ArrayList<>();
	}

	public T dealCard() {
		return deck.get(dealtIndex++);
	}

	public T[] dealHand(int num) {
		T[] cards = (T[]) Arrays.copyOfRange(deck.toArray(), dealtIndex, dealtIndex + num);
		dealtIndex += num;
		return cards;
	}

	public int remainingCards() {
		return deck.size() - dealtIndex;
	}

	public void setDeck(List<T> deck) {
		this.deck = deck;
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public List<T> getDeck() {
		return deck;
	}
}
