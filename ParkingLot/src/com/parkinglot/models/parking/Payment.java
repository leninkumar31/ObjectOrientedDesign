package com.parkinglot.models.parking;

import java.time.LocalDateTime;

public abstract class Payment {
	private String id;
	private String tickerId;
	private double amount;
	
	private LocalDateTime paymentStartDate;
	private LocalDateTime paymentCompletedDate;
	private PaymentStatus paymentStatus;

	public Payment(String id, String ticketId, double amount) {
		this.id = id;
		this.tickerId = ticketId;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public String getTickerId() {
		return tickerId;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDateTime getPaymentStartDate() {
		return paymentStartDate;
	}

	public void setPaymentStartDate(LocalDateTime paymentStartDate) {
		this.paymentStartDate = paymentStartDate;
	}

	public LocalDateTime getPaymentCompletedDate() {
		return paymentCompletedDate;
	}

	public void setPaymentCompletedDate(LocalDateTime paymentCompletedDate) {
		this.paymentCompletedDate = paymentCompletedDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public abstract void makePayment();
	
}
