package com.parkinglot.models.vehicle;

import com.parkinglot.models.parking.ParkingTicket;

public abstract class Vehicle {
	private String licenceNumber;
	private String color;
	private final VehicleType vehicleType;
	private ParkingTicket ticket;

	public Vehicle(String licenceNumber, String color, VehicleType vehicleType) {
		super();
		this.licenceNumber = licenceNumber;
		this.color = color;
		this.vehicleType = vehicleType;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public ParkingTicket getTicket() {
		return ticket;
	}

	public void setTicket(ParkingTicket ticket) {
		this.ticket = ticket;
	}

}
