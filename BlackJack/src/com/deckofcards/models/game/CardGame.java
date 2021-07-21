package com.deckofcards.models.game;

import com.deckofcards.models.blackjack.BlackJackGame;

public abstract class CardGame {
	public static CardGame createCardGame(GameType type) {
		switch(type){
			case BLACKJACK:
				return new BlackJackGame();
			default:
				System.out.println("unknown value: "+type);
		}
		return null;
	}
}
