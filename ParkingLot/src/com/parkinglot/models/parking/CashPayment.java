package com.parkinglot.models.parking;

public class CashPayment extends Payment{

	public CashPayment(String id, String ticketId, double amount) {
		super(id, ticketId, amount);
	}

	@Override
	public void makePayment() {
		super.setPaymentStatus(PaymentStatus.SUCCESS);
	}

}
