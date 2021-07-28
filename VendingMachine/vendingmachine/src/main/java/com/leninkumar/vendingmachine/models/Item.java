package com.leninkumar.vendingmachine.models;

public enum Item {
	PEPSI("pepsi", 20), COKE("coke", 30), SODA("soda", 40);

	private Item(String name, int val) {
		this.name = name;
		this.val = val;
	}

	private final String name;
	private final int val;

	public String getName() {
		return name;
	}

	public int getVal() {
		return val;
	}

	public static Item getItemFromName(String name) {
		switch (name) {
		case "pepsi":
			return PEPSI;
		case "coke":
			return COKE;
		case "soda":
			return SODA;
		}
		return null;
	}
}
