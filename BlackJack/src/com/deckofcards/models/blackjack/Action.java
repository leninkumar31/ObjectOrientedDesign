package com.deckofcards.models.blackjack;

import com.deckofcards.models.exceptions.InvalidActionException;

public enum Action {
	HIT, STAND, DOUBLE;

	public static Action getAction(String action) throws InvalidActionException {
		if ("hit".equals(action)) {
			return HIT;
		} else if ("stand".equals(action)) {
			return STAND;
		} else if ("double".equals(action)) {
			return DOUBLE;
		}
		throw new InvalidActionException("Given action is not present:" + action);
	}
}
