package com.leninkumar.cabbooking.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.leninkumar.cabbooking.models.account.Driver;
import com.leninkumar.cabbooking.models.account.DriverStatus;
import com.leninkumar.cabbooking.models.location.GeoLocation;

public class DriverRepository {
	public static List<Driver> driverList = new ArrayList<>();
	public static Map<String, Driver> driverMap = new HashMap<>();

	public static void addDriver(Driver driver) {
		driverList.add(driver);
		driverMap.put(driver.getUserName(), driver);
	}

	public static Optional<Driver> findDriver(final GeoLocation location) {
		Optional<Driver> firstDriver = driverList.stream()
				.filter(driver -> driver.getDriverStatus().equals(DriverStatus.AVAILABLE)
						&& location.isWithInRange(driver.getLocation()))
				.findFirst();
		return firstDriver;
	}
}
