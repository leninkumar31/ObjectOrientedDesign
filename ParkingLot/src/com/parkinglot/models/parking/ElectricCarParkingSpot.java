package com.parkinglot.models.parking;

public class ElectricCarParkingSpot extends ParkingSpot{

	public ElectricCarParkingSpot(String parkingSpotId) {
		super(parkingSpotId, ParkingSpotType.ELECTRIC);
	}

}
