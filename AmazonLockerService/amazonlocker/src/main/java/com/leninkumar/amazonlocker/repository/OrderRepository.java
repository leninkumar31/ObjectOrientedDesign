package com.leninkumar.amazonlocker.repository;

import java.util.HashMap;
import java.util.Map;

import com.leninkumar.amazonlocker.models.Order;

public class OrderRepository {
	public static Map<String, Order> orderMap = new HashMap<>();

	public static Order getOrder(String orderId) {
		return orderMap.get(orderId);
	}
}
