package com.parkinglot.models.vehicle;

public class Car extends Vehicle{

	public Car(String licenceNumber, String color) {
		super(licenceNumber, color, VehicleType.CAR);
	}

}
