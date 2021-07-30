package com.leninkumar.amazonlocker.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.leninkumar.amazonlocker.models.LockerLocation;

public class LockerLocationRepository {
	public static List<LockerLocation> lockerLocations = new ArrayList<>();

	public static LockerLocation getLockerLocation(String locationId) {
		Optional<LockerLocation> lockerLocation = lockerLocations.stream()
				.filter(location -> location.getLocationId().equals(locationId)).findFirst();
		if (lockerLocation.isPresent()) {
			return lockerLocation.get();
		}
		return null;
	}
}
