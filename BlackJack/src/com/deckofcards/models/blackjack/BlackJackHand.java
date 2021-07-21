package com.deckofcards.models.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.deckofcards.models.card.Rank;
import com.deckofcards.models.hand.Hand;

public class BlackJackHand extends Hand<BlackJackCard> {

	@Override
	public int score() {
		List<Integer> possibleScores = getPossibleScores();
		int minOver = Integer.MAX_VALUE;
		int maxUnder = Integer.MIN_VALUE;
		for (Integer score : possibleScores) {
			if (score > 21) {
				minOver = Math.min(minOver, score);
			} else {
				maxUnder = Math.max(maxUnder, score);
			}
		}
		return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
	}

	public boolean isBlackJack() {
		if (this.getCards().size() != 2) {
			return false;
		}
		if (this.getCards().get(0).getRank() == Rank.ACE && this.getCards().get(1).getValue() == 10) {
			return true;
		}
		return this.getCards().get(1).getRank() == Rank.ACE && this.getCards().get(0).getValue() == 10;
	}

	public boolean is21() {
		return this.score() == 21;
	}

	public boolean busted() {
		return this.score() > 21;
	}

	private List<Integer> getPossibleScores() {
		List<Integer> scores = new ArrayList<>();
		int numAces = 0;
		int sum = 0;
		for (BlackJackCard card : this.getCards()) {
			if (card.getRank() == Rank.ACE) {
				numAces++;
			} else {
				sum += card.getValue();
			}
		}
		if (numAces > 0) {
			combinitionSum(numAces, scores, sum);
		} else {
			scores.add(sum);
		}
		return scores;
	}

	private void combinitionSum(int cnt, List<Integer> scores, int sum) {
		if (cnt == 0) {
			scores.add(sum);
			return;
		}
		combinitionSum(cnt - 1, scores, sum + 1);
		combinitionSum(cnt - 1, scores, sum + 11);
	}
}
