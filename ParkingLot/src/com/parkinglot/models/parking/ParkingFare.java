package com.parkinglot.models.parking;

import java.util.HashMap;
import java.util.Map;

public class ParkingFare {
	Map<ParkingSpotType, Double> hourlyCost = new HashMap<>();

	public ParkingFare() {
		hourlyCost.put(ParkingSpotType.CAR, 20.0);
		hourlyCost.put(ParkingSpotType.LARGE, 30.0);
		hourlyCost.put(ParkingSpotType.ABLED, 15.0);
		hourlyCost.put(ParkingSpotType.MOTORBIKE, 10.0);
		hourlyCost.put(ParkingSpotType.ELECTRIC, 25.0);
	}

	public double getParkingFare(ParkingSpotType spotType) {
		return hourlyCost.get(spotType);
	}
}
