package com.leninkumar.amazonlocker.service;

import com.leninkumar.amazonlocker.models.Order;
import com.leninkumar.amazonlocker.repository.OrderRepository;

public class OrdersService {

	public Order getOrder(String orderId) {
		return OrderRepository.getOrder(orderId);
	}
}
