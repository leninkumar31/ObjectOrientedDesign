package com.leninkumar.cabbooking.models.vehicle;

public enum VehicleType {
	MOTORCYCLE(15.0), AUTO(20.0), HATCHBACK(25.0), SEDAN(30.0), SUV(35.0);
	
	private VehicleType(double price) {
		this.kmPrice = price;
	}

	private double kmPrice;

	public double getKmPrice() {
		return kmPrice;
	}

	public void setKmPrice(double kmPrice) {
		this.kmPrice = kmPrice;
	}

}
