package com.leninkumar.amazonlocker.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.leninkumar.amazonlocker.models.LockerPackage;

public class LockerPackageRepository {
	public static List<LockerPackage> lockerPackages = new ArrayList<>();
	
	public static Optional<LockerPackage> getLockerByLockerId(String lockerId) {
		return lockerPackages.stream().filter(locker -> locker.getLockerId().equals(lockerId)).findFirst();
	}
}
