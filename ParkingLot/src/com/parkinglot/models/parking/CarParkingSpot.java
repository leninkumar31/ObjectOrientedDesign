package com.parkinglot.models.parking;

public class CarParkingSpot extends ParkingSpot {

	public CarParkingSpot(String parkingSpotId) {
		super(parkingSpotId, ParkingSpotType.CAR);
	}

}
