package com.leninkumar.connect4.game;

public enum Symbol {
	BLUE("B"), RED("R"), BLANK("X");

	private final String value;

	Symbol(String symbol) {
		this.value = symbol;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
