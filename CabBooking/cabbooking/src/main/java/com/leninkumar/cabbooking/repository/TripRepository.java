package com.leninkumar.cabbooking.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leninkumar.cabbooking.models.account.trip.Trip;

public class TripRepository {
	public static List<Trip> tripList = new ArrayList<>();
	public static Map<String, List<Trip>> driverTrips = new HashMap<>();
	public static Map<String, List<Trip>> userTrips = new HashMap<>();
	
	public static void addTrip(Trip trip) {
		tripList.add(trip);
		if(!userTrips.containsKey(trip.getUserId())) {
			userTrips.put(trip.getUserId(), new ArrayList<>());
		}
		userTrips.get(trip.getUserId()).add(trip);
		if(!driverTrips.containsKey(trip.getVehicle().getDriver())) {
			driverTrips.put(trip.getVehicle().getDriver(), new ArrayList<>());
		}
		driverTrips.get(trip.getVehicle().getDriver()).add(trip);
	}
}
