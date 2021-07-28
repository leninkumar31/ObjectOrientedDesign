package com.leninkumar.vendingmachine.models;

import com.leninkumar.vendingmachine.exceptions.InvalidCoinValueException;

public enum Coin {
	ONE(1), TWO(2), FIVE(5), TEN(10), TWENTY(20);

	private Coin(int val) {
		this.val = val;
	}

	private final int val;

	public int getVal() {
		return val;
	}

	public static Coin getCoin(int val) throws InvalidCoinValueException {
		switch (val) {
		case 1:
			return Coin.ONE;
		case 2:
			return Coin.TWO;
		case 5:
			return Coin.FIVE;
		case 10:
			return Coin.TEN;
		case 20:
			return Coin.TWENTY;
		}
		throw new InvalidCoinValueException("Given value: " + val + " doesn't belong to any of the Coins");
	}
}
