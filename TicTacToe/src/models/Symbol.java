package models;

public enum Symbol {
	CROSS("X"), CIRCLE("O"), BLANK(" ");

	private final String value;

	private Symbol(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
