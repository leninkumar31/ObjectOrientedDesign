package com.leninkumar.cabbooking.models.account.trip;

import java.time.LocalDateTime;
import java.util.UUID;

import com.leninkumar.cabbooking.models.location.Address;
import com.leninkumar.cabbooking.models.location.GeoLocation;
import com.leninkumar.cabbooking.models.vehicle.Vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Trip {
	private String tripId;
	private String userId;
	private Vehicle vehicle;
	private Address pickupLocation;
	private Address dropLocation;
	private double amount;
	@Setter
	private GeoLocation currLocation;
	@Setter
	private LocalDateTime startTime;
	@Setter
	private LocalDateTime endTime;
	@Setter
	private TripStatus status;
	
	public Trip(String userId, Vehicle vehicle, Address pickupLocation, Address dropLocation, double amount) {
		this.tripId = UUID.randomUUID().toString();
		this.userId = userId;
		this.vehicle = vehicle;
		this.pickupLocation = pickupLocation;
		this.dropLocation = dropLocation;
		this.amount = amount;
		this.status = TripStatus.BOOKED;
	}
}
