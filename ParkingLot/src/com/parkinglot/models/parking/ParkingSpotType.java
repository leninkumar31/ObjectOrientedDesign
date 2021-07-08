package com.parkinglot.models.parking;

import com.parkinglot.models.vehicle.VehicleType;

public enum ParkingSpotType {
	ABLED, CAR, LARGE, ELECTRIC, MOTORBIKE;

	public static ParkingSpotType getSpotTypeForVehicle(VehicleType vehicleType) {
		switch (vehicleType) {
		case MOTORBIKE:
			return ParkingSpotType.MOTORBIKE;
		case CAR:
			return ParkingSpotType.CAR;
		case ELECTRIC:
			return ParkingSpotType.ELECTRIC;
		default:
			return ParkingSpotType.LARGE;
		}
	}
}
