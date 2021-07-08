package com.parkinglot.models.vehicle;

public class Truck extends Vehicle{

	public Truck(String licenceNumber, String color) {
		super(licenceNumber, color, VehicleType.TRUCK);
	}
	
}
