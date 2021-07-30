package com.leninkumar.amazonlocker.service;

import java.util.UUID;

public class CustomerService {
	public String getCustomerIdFromOrder(String orderId) {
		return UUID.randomUUID().toString();
	}
}
