package com.leninkumar.vendingmachine.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.leninkumar.vendingmachine.exceptions.InsufficientAmountException;
import com.leninkumar.vendingmachine.exceptions.InsufficientChangeException;
import com.leninkumar.vendingmachine.exceptions.ItemSoldOutException;

public class VendingMachine {
	private Map<Coin, Integer> coinInventory;

	private Map<Item, Integer> itemInventory;

	private List<Rack> vendingRacks;

	private static volatile VendingMachine INSTANCE = null;

	private Item selectedItem = null;

	private int currentSum = 0;

	private int totalBal;

	private int totalSales = 0;

	private List<Coin> insertedCoins;

	public static VendingMachine getInstance(int numRacks, int numCols, int numSpots) {
		if (INSTANCE == null) {
			synchronized (VendingMachine.class) {
				if (INSTANCE == null) {
					INSTANCE = new VendingMachine(numRacks, numCols, numSpots);
				}
			}
		}
		return INSTANCE;
	}

	private VendingMachine(int numRacks, int numCols, int numSpots) {
		this.totalBal = 0;
		this.totalSales = 0;
		this.coinInventory = new HashMap<>();
		this.itemInventory = new HashMap<>();
		this.vendingRacks = new LinkedList<>();
		for (int i = 0; i < numRacks; i++) {
			this.vendingRacks.add(new Rack(i, numCols, numSpots));
		}
	}

	public int selectAndGetItemPrice(Item item) throws ItemSoldOutException {
		if (isItemAvailable(item)) {
			this.setSelectedItem(item);
			return item.getVal();
		}
		throw new ItemSoldOutException(item + " is not available");
	}

	public boolean insertCoins(Coin coin) {
		if (this.insertedCoins == null) {
			this.insertedCoins = new ArrayList<>();
		}
		this.currentSum += coin.getVal();
		this.addToCoinInventory(coin);
		this.insertedCoins.add(coin);
		return this.currentSum >= this.selectedItem.getVal();
	}

	public Response collectItemAndChange() {
		Item item;
		List<Coin> change;
		try {
			item = collectItem();
			change = getChange(this.currentSum - item.getVal());
		} catch (InsufficientAmountException | InsufficientChangeException e) {
			return refund();
		}
		this.totalSales += item.getVal();
		updateCoinInventory(change);
		this.selectedItem = null;
		this.currentSum = 0;
		this.insertedCoins = null;
		return new Response(item, change);
	}

	private Response refund() {
		List<Coin> change = this.getInsertedCoins();
		updateCoinInventory(change);
		this.selectedItem = null;
		this.currentSum = 0;
		this.insertedCoins = null;
		return new Response(null, change);
	}

	private void updateCoinInventory(List<Coin> change) {
		for (Coin coin : change) {
			this.deductFromCoinInventory(coin);
		}
	}

	private Item collectItem() throws InsufficientAmountException, InsufficientChangeException {
		if (this.selectedItem.getVal() > this.currentSum) {
			throw new InsufficientAmountException("Inserted money in not enough");
		}
		if (hasSuffiecientChange(this.currentSum - this.selectedItem.getVal())) {
			this.deductFromItemInventory(selectedItem);
			// remove from the rack also
			return selectedItem;
		}
		throw new InsufficientChangeException("Not enough change available");
	}

	private boolean hasSuffiecientChange(int val) {
		try {
			getChange(val);
			return true;
		} catch (InsufficientChangeException e) {
			return false;
		}

	}

	private List<Coin> getChange(int val) throws InsufficientChangeException {
		List<Coin> change = new ArrayList<>();
		if(val == 0) {
			return change;
		}
		Coin[] coins = Coin.values();
		int changeSum = val;
		for (int i = coins.length - 1; i >= 0; i--) {
			if (val >= coins[i].getVal()) {
				int cnt = Math.min(changeSum / coins[i].getVal(), this.coinInventory.get(coins[i]));
				changeSum -= coins[i].getVal() * cnt;
				for (int j = 0; j < cnt; j++) {
					change.add(coins[i]);
				}
				if (changeSum == 0) {
					return change;
				}
			}
		}
		throw new InsufficientChangeException("Not enough change available");
	}

	public void addToCoinInventory(Coin coin) {
		this.coinInventory.put(coin, this.coinInventory.getOrDefault(coin, 0) + 1);
		this.totalBal += coin.getVal();
	}

	public void deductFromCoinInventory(Coin coin) {
		this.coinInventory.put(coin, this.coinInventory.getOrDefault(coin, 0) - 1);
		this.totalBal -= coin.getVal();
	}

	public void addToItemInventory(Item item) {
		this.itemInventory.put(item, this.itemInventory.getOrDefault(item, 0) + 1);
	}

	public void deductFromItemInventory(Item item) {
		this.itemInventory.put(item, this.itemInventory.getOrDefault(item, 0) - 1);
	}

	public boolean isItemAvailable(Item item) {
		return this.getItemInventory().containsKey(item) && this.getItemInventory().get(item) > 0;
	}

	public Map<Item, Integer> getItemInventory() {
		return this.itemInventory;
	}

	public Item getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<Rack> getVendingRacks() {
		return vendingRacks;
	}

	public int getTotalSales() {
		return this.totalSales;
	}

	public int getTotalBal() {
		return this.totalBal;
	}

	public List<Coin> getInsertedCoins() {
		return insertedCoins;
	}

	public void setInsertedCoins(List<Coin> insertedCoins) {
		this.insertedCoins = insertedCoins;
	}

}
