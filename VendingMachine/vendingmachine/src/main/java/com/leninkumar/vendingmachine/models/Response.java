package com.leninkumar.vendingmachine.models;

import java.util.List;

public class Response {
	Item item;
	List<Coin> remainingChange;

	public Response(Item item, List<Coin> remainingChange) {
		this.item = item;
		this.remainingChange = remainingChange;
	}

	public Item getItem() {
		return item;
	}

	public List<Coin> getRemainingChange() {
		return remainingChange;
	}
	
	
}
