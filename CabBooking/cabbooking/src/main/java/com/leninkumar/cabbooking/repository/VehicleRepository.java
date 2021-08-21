package com.leninkumar.cabbooking.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leninkumar.cabbooking.models.vehicle.Vehicle;

public class VehicleRepository {
	public static List<Vehicle> vehicleList = new ArrayList<>();
	public static Map<String, Vehicle> vehicleMap = new HashMap<>();
	
	public static void addVehicle(Vehicle vehicle) {
		vehicleList.add(vehicle);
		vehicleMap.put(vehicle.getDriver(), vehicle);
	}
	
	public static Vehicle getVehicle(String driver){
		return vehicleMap.get(driver);
	}
}
