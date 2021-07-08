package com.parkinglot.models.parking;

public class CardPayment extends Payment{

	public CardPayment(String id, String ticketId, double amount) {
		super(id, ticketId, amount);
	}

	@Override
	public void makePayment() {
		super.setPaymentStatus(PaymentStatus.SUCCESS);
	}

}
