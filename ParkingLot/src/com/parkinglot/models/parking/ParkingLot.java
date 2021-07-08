package com.parkinglot.models.parking;

import java.util.ArrayList;
import java.util.List;

import com.parkinglot.models.Common.Address;
import com.parkinglot.models.vehicle.VehicleType;

public class ParkingLot {
	private String parkingLotId;
	private Address address;
	private List<ParkingFloor> parkingFloors;
	private List<EntrancePanel> entrancePanels;
	private List<ExitPanel> exitPanels;

	public static final ParkingLot INSTANCE = new ParkingLot();

	private ParkingLot() {
		this.parkingFloors = new ArrayList<>();
		this.entrancePanels = new ArrayList<>();
		this.exitPanels = new ArrayList<>();
	}

	public boolean isFull() {
		int fullFloors = 0;
		for (ParkingFloor floor : parkingFloors) {
			if (floor.isFloorFull()) {
				fullFloors++;
			}
		}
		return fullFloors == parkingFloors.size();
	}
	
	public boolean canPark(VehicleType vehicleType) {
		for (ParkingFloor floor : parkingFloors) {
			if(floor.canPark(ParkingSpotType.getSpotTypeForVehicle(vehicleType))) {
				return true;
			}
		}
		return false;
	}
	
	public ParkingSpot getParkingSpot(VehicleType vehicleType) {
		for (ParkingFloor floor : parkingFloors) {
			ParkingSpot spot = floor.getParkingSpot(ParkingSpotType.getSpotTypeForVehicle(vehicleType));
			if(spot != null) {
				return spot;
			}
		}
		return null;
	}
	
	public ParkingSpot vacateParkingSpot(String parkingSpotId) {
		for (ParkingFloor floor : parkingFloors) {
			ParkingSpot spot = floor.vacateSpot(parkingSpotId);
			if(spot != null) {
				return spot;
			}
		}
		return null;
	}

	public void setParkingLotId(String parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getParkingLotId() {
		return parkingLotId;
	}

	public Address getAddress() {
		return address;
	}

	public List<ParkingFloor> getParkingFloors() {
		return parkingFloors;
	}

	public List<EntrancePanel> getEntrancePanels() {
		return entrancePanels;
	}

	public List<ExitPanel> getExitPanels() {
		return exitPanels;
	}

	public static ParkingLot getInstance() {
		return INSTANCE;
	}

}
