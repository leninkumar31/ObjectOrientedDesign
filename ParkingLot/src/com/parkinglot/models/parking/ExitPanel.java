package com.parkinglot.models.parking;

import java.time.Duration;
import java.time.LocalDateTime;

public class ExitPanel {
	private String id;

	public ExitPanel(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public ParkingTicket scanAndVacate(ParkingTicket parkingTicket) {
		ParkingSpot parkingSpot = ParkingLot.INSTANCE.vacateParkingSpot(parkingTicket.getAssignedSlotId());
		parkingTicket.setAmount(caclculateTotalCost(parkingTicket, parkingSpot.getSpotType()));
		return parkingTicket;
	}
	
	private double caclculateTotalCost(ParkingTicket parkingTicket, ParkingSpotType spotType) {
		Duration duration = Duration.between(parkingTicket.getIssuedAt(), LocalDateTime.now());
		long hours = duration.toHours();
		if(hours == 0) {
			hours = 1;
		}
		return hours * new ParkingFare().getParkingFare(spotType);
	}
}
