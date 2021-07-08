package com.parkinglot.models.vehicle;

public class ElectricCar extends Vehicle {

	public ElectricCar(String licenceNumber, String color) {
		super(licenceNumber, color, VehicleType.ELECTRIC);
	}

}
