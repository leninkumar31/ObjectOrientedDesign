package com.leninkumar.amazonlocker.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.leninkumar.amazonlocker.models.GeoLocation;
import com.leninkumar.amazonlocker.models.Locker;
import com.leninkumar.amazonlocker.models.LockerSize;
import com.leninkumar.amazonlocker.models.LockerStatus;

public class LockerRepository {

	public static List<Locker> lockers;
	public static Map<String, Locker> lockerMap;

	static {
		lockers = new ArrayList<>();
		lockerMap = new HashMap<>();
	}

	public static Locker getLocker(LockerSize size, GeoLocation location) {
		List<Locker> lockerList = lockers.stream()
				.filter(locker -> locker.getStatus().equals(LockerStatus.AVAILABLE) && locker.getSize().equals(size))
				.collect(Collectors.toList());
		if (lockerList != null && lockerList.size() > 0) {
			return lockerList.get(0);
		}
		return null;
	}
}
