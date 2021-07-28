package com.leninkumar.vendingmachine.models;

public class Spot {
	private boolean isAvailable;
	private Item item;

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public Item getItem() {
		return item;
	}

}
