package com.leninkumar.cabbooking.service;

import com.leninkumar.cabbooking.models.account.trip.Trip;
import com.leninkumar.cabbooking.models.location.Address;
import com.leninkumar.cabbooking.models.vehicle.Vehicle;

public interface TripService {
	public Trip createTrip(String userId, Vehicle vehicle, Address pickupLocation, Address dropLocation);
}
