package com.parkinglot.models.parking;

public abstract class ParkingSpot {
	private String parkingSpotId;
	private boolean isFree;
	private ParkingSpotType spotType;
	private String assignedVehicleId;

	public ParkingSpot(String parkingSpotId, ParkingSpotType spotType) {
		this.parkingSpotId = parkingSpotId;
		this.spotType = spotType;
	}

	public void assignVehicleId(String vehicleId) {
		this.assignedVehicleId = vehicleId;
	}

	public void freeSpot() {
		this.isFree = true;
		this.assignedVehicleId = null;
	}

	public String getParkingSpotId() {
		return parkingSpotId;
	}

	public void setParkingSpotId(String parkingSpotId) {
		this.parkingSpotId = parkingSpotId;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public ParkingSpotType getSpotType() {
		return spotType;
	}

	public void setSpotType(ParkingSpotType spotType) {
		this.spotType = spotType;
	}

	public String getAssignedVehicleId() {
		return assignedVehicleId;
	}

	public void setAssignedVehicleId(String assignedVehicleId) {
		this.assignedVehicleId = assignedVehicleId;
	}

}
