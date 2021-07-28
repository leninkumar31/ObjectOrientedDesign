package com.leninkumar.vendingmachine.controller;

import java.util.Scanner;

import com.leninkumar.vendingmachine.exceptions.InvalidCoinValueException;
import com.leninkumar.vendingmachine.exceptions.ItemSoldOutException;
import com.leninkumar.vendingmachine.models.Coin;
import com.leninkumar.vendingmachine.models.Column;
import com.leninkumar.vendingmachine.models.Item;
import com.leninkumar.vendingmachine.models.Rack;
import com.leninkumar.vendingmachine.models.Response;
import com.leninkumar.vendingmachine.models.VendingMachine;

public class VendingMachineController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		VendingMachine INSTANCE = VendingMachine.getInstance(3, 2, 2);
		for (Coin coin : Coin.values()) {
			for (int i = 0; i < 10; i++) {
				INSTANCE.addToCoinInventory(coin);
			}
		}
		Item[] items = Item.values();
		for (int i = 0; i < INSTANCE.getVendingRacks().size(); i++) {
			Rack currRack = INSTANCE.getVendingRacks().get(i);
			for (int j = 0; j < currRack.getNumCols(); j++) {
				Column col = currRack.getVendingColumns().get(j);
				for (int k = 0; k < col.getNumSpots(); k++) {
					col.getSpots().get(k).setAvailable(true);
					col.getSpots().get(k).setItem(items[i]);
					INSTANCE.addToItemInventory(items[i]);
				}
			}
		}
		String itemName = sc.next();
		Item item = Item.getItemFromName(itemName);
		try {
			int val = INSTANCE.selectAndGetItemPrice(item);
			System.out.println(item + " value is: " + val);
		} catch (ItemSoldOutException e) {
			System.out.println(item + " doesn't exist");
		}
		while (true) {
			int val = sc.nextInt();
			Coin coin;
			try {
				coin = Coin.getCoin(val);
				boolean isEnough = INSTANCE.insertCoins(coin);
				if (isEnough) {
					break;
				}
			} catch (InvalidCoinValueException e) {
				System.out.println("Invalid input. Please enter one of 1,2,5,10,20");
			}
		}
		Response resp = INSTANCE.collectItemAndChange();
		System.out.println(resp.getItem());
		System.out.println(resp.getRemainingChange().toString());
		sc.close();
	}

}
