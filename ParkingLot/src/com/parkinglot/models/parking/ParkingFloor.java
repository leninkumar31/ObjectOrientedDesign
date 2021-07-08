package com.parkinglot.models.parking;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ParkingFloor {
	private String floorId;
	private Map<ParkingSpotType, Deque<ParkingSpot>> parkingSpots;
	private Map<String, ParkingSpot> usedParkingSpots;

	public ParkingFloor(String floorId) {
		this.floorId = floorId;
		this.usedParkingSpots = new HashMap<>();
		this.parkingSpots = new HashMap<>();
		this.parkingSpots.put(ParkingSpotType.CAR, new ConcurrentLinkedDeque<>());
		this.parkingSpots.put(ParkingSpotType.ELECTRIC, new ConcurrentLinkedDeque<>());
		this.parkingSpots.put(ParkingSpotType.ABLED, new ConcurrentLinkedDeque<>());
		this.parkingSpots.put(ParkingSpotType.LARGE, new ConcurrentLinkedDeque<>());
		this.parkingSpots.put(ParkingSpotType.MOTORBIKE, new ConcurrentLinkedDeque<>());
	}

	public boolean isFloorFull() {
		int floorCnt = 0;
		for (Map.Entry<ParkingSpotType, Deque<ParkingSpot>> entry : parkingSpots.entrySet()) {
			if (entry.getValue().size() == 0) {
				floorCnt++;
			}
		}
		return parkingSpots.size() == floorCnt;
	}

	public boolean canPark(ParkingSpotType spotType) {
		return parkingSpots.get(spotType).size() > 0;
	}

	public synchronized ParkingSpot getParkingSpot(ParkingSpotType spotType) {
		if (!canPark(spotType)) {
			return null;
		}
		ParkingSpot spot = parkingSpots.get(spotType).poll();
		usedParkingSpots.put(spot.getParkingSpotId(), spot);
		return spot;
	}

	public ParkingSpot vacateSpot(String spotId) {
		ParkingSpot spot = usedParkingSpots.get(spotId);
		if(spot != null) {
			spot.freeSpot();
			parkingSpots.get(spot.getSpotType()).addFirst(spot);
			return spot;
		}
		return null;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public Map<ParkingSpotType, Deque<ParkingSpot>> getParkingSpots() {
		return parkingSpots;
	}

	public Map<String, ParkingSpot> getUsedParkingSpots() {
		return usedParkingSpots;
	}

}
