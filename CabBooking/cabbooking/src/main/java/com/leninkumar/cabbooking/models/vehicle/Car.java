package com.leninkumar.cabbooking.models.vehicle;

public class Car extends Vehicle{
	public Car(String regNumber, VehicleType vehicleType, String driver, String model, EngineType engine,
			TransmissionType transmission, String maker, int numPassengers) {
		super(regNumber, vehicleType, driver, model, engine, transmission, maker, numPassengers);
	}
}
