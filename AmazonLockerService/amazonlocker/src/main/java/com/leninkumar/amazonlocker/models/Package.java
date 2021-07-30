package com.leninkumar.amazonlocker.models;

import java.util.List;
import java.util.UUID;

import lombok.Getter;

@Getter
public class Package {
	private String packageId;
	private String orderId;
	private List<Item> items;
	private double packageSize;

	public Package(String orderId, List<Item> items) {
		this.packageId = UUID.randomUUID().toString();
		this.orderId = orderId;
		this.items = items;
		this.packageSize = Math.random() * 10;
	}
}
