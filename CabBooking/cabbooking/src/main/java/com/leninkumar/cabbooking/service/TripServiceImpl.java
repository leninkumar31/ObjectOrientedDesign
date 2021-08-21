package com.leninkumar.cabbooking.service;

import com.leninkumar.cabbooking.models.account.trip.Trip;
import com.leninkumar.cabbooking.models.location.Address;
import com.leninkumar.cabbooking.models.location.GeoLocation;
import com.leninkumar.cabbooking.models.vehicle.Vehicle;
import com.leninkumar.cabbooking.models.vehicle.VehicleType;
import com.leninkumar.cabbooking.repository.TripRepository;

public class TripServiceImpl implements TripService{

	@Override
	public Trip createTrip(String userId, Vehicle vehicle, Address pickupLocation, Address dropLocation) {
		double amount = calculateFare(vehicle.getVehicleType(), pickupLocation.getLocation(), dropLocation.getLocation());
		Trip trip = new Trip(userId, vehicle, pickupLocation, dropLocation, amount);
		TripRepository.addTrip(trip);
		return trip;
	}
	
	private double calculateFare(VehicleType type, GeoLocation pickupLocation, GeoLocation dropLocation) {
		return type.getKmPrice() * pickupLocation.getDistance(dropLocation);
	}
}
