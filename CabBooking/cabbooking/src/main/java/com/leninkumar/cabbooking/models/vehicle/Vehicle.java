package com.leninkumar.cabbooking.models.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Vehicle {
	private String regNumber;
	private VehicleType vehicleType;
	private String driver;
	private String model;
	private EngineType engine;
	private TransmissionType transmission;
	private String maker;
	private int numPassengers;
}
