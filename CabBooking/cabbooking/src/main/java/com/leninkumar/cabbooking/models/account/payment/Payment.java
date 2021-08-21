package com.leninkumar.cabbooking.models.account.payment;

public abstract class Payment {
	private String paymentId;
	private double amount;
	private PaymentStatus status;
	private PaymentType type;
	private String tripId;
}
