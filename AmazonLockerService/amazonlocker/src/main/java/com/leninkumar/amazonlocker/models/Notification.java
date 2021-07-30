package com.leninkumar.amazonlocker.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Notification {
	private String customerId;
	private String orderId;
	private String lockerId;
	private String code;
}
