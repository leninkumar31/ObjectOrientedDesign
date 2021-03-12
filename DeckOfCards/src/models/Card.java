package models;

public class Card {
	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank){
		this.suit = suit;
		this.rank = rank;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	@Override
	public String toString(){
		return this.getRank().toString() + " Of " + this.getSuit().toString();
	}
}
