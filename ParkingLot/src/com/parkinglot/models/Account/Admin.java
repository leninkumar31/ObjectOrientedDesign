package com.parkinglot.models.Account;

import java.util.Optional;

import com.parkinglot.exceptions.InvalidParkingFloorException;
import com.parkinglot.models.parking.EntrancePanel;
import com.parkinglot.models.parking.ExitPanel;
import com.parkinglot.models.parking.ParkingFloor;
import com.parkinglot.models.parking.ParkingLot;
import com.parkinglot.models.parking.ParkingSpot;

public class Admin extends Account {
	public void addParkingFloor(ParkingFloor parkingFloor) {
		Optional<ParkingFloor> pFloor = ParkingLot.INSTANCE.getParkingFloors().stream()
				.filter(floor -> floor.getFloorId().equalsIgnoreCase(parkingFloor.getFloorId())).findFirst();
		if (pFloor.isPresent()) {
			return;
		}
		ParkingLot.INSTANCE.getParkingFloors().add(parkingFloor);
	}

	public void addParkingSpot(String parkingFloorId, ParkingSpot parkingSpot) throws InvalidParkingFloorException {
		Optional<ParkingFloor> pFloor = ParkingLot.INSTANCE.getParkingFloors().stream()
				.filter(floor -> floor.getFloorId().equalsIgnoreCase(parkingFloorId)).findFirst();
		if (!pFloor.isPresent()) {
			throw new InvalidParkingFloorException("Invalid Floor");
		}

		Optional<ParkingSpot> pSpot = pFloor.get().getParkingSpots().get(parkingSpot.getSpotType()).stream()
				.filter(spot -> spot.getParkingSpotId().equalsIgnoreCase(parkingSpot.getParkingSpotId())).findFirst();
		if (pSpot.isPresent()) {
			return;
		}
		pFloor.get().getParkingSpots().get(parkingSpot.getSpotType()).add(parkingSpot);
	}

	public void addEntrancePanel(EntrancePanel entrancePanel) {
		Optional<EntrancePanel> panel = ParkingLot.INSTANCE.getEntrancePanels().stream()
				.filter(ePanel -> ePanel.getId().equalsIgnoreCase(entrancePanel.getId())).findFirst();
		if (panel.isPresent()) {
			return;
		}
		ParkingLot.INSTANCE.getEntrancePanels().add(entrancePanel);
	}

	public void addExitPanel(ExitPanel exitPanel) {
		Optional<ExitPanel> panel = ParkingLot.INSTANCE.getExitPanels().stream()
				.filter(ePanel -> ePanel.getId().equalsIgnoreCase(exitPanel.getId())).findFirst();
		if (panel.isPresent()) {
			return;
		}
		ParkingLot.INSTANCE.getExitPanels().add(exitPanel);
	}
}
