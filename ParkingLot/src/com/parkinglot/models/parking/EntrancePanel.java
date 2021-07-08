package com.parkinglot.models.parking;

import com.parkinglot.models.vehicle.Vehicle;

public class EntrancePanel {
	private String id;

	public EntrancePanel(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public ParkingTicket getParkingTicket(Vehicle vehicle) {
		ParkingSpot spot = ParkingLot.INSTANCE.getParkingSpot(vehicle.getVehicleType());
		if(spot == null) {
			return null;
		}
		return ParkingTicket.buildParkingTicket(vehicle.getLicenceNumber(), spot.getParkingSpotId());
	}
	
}
