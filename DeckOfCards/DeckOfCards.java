import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
	List<Card> deck;
	public DeckOfCards() {
		deck = new ArrayList<>();
		for(Suit suit: Suit.values()) {
			for(Rank rank: Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}
	}
	
	public void PrintDeck() {
		for(Card card: deck) {
			System.out.println(card.toString());
		}
	}
	
	public void Shuffle() {
		Collections.shuffle(deck);
	}
}
