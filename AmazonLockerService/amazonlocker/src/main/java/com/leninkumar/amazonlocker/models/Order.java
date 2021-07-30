package com.leninkumar.amazonlocker.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
	private String orderId;
	List<Item> items;
	GeoLocation deliveryLocation;
}
