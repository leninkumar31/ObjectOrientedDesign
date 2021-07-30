package com.leninkumar.amazonlocker.service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Optional;

import com.leninkumar.amazonlocker.exceptions.LockerCodeMisMatchException;
import com.leninkumar.amazonlocker.exceptions.LockerPackageNotFoundException;
import com.leninkumar.amazonlocker.exceptions.PackPickupTimeExceededException;
import com.leninkumar.amazonlocker.exceptions.PickupCodeExpiredException;
import com.leninkumar.amazonlocker.models.GeoLocation;
import com.leninkumar.amazonlocker.models.Locker;
import com.leninkumar.amazonlocker.models.LockerLocation;
import com.leninkumar.amazonlocker.models.LockerPackage;
import com.leninkumar.amazonlocker.models.LockerSize;
import com.leninkumar.amazonlocker.models.LockerStatus;
import com.leninkumar.amazonlocker.models.Timing;
import com.leninkumar.amazonlocker.repository.LockerLocationRepository;
import com.leninkumar.amazonlocker.repository.LockerPackageRepository;
import com.leninkumar.amazonlocker.repository.LockerRepository;

public class LockerService {
	public Locker getLocker(LockerSize size, GeoLocation location) {
		Locker locker = LockerRepository.getLocker(size, location);
		locker.setStatus(LockerStatus.ASSIGNED);
		return locker;
	}
	
	public void pickFromLocker(String lockerId, String code, LocalDateTime currDate) throws LockerPackageNotFoundException, LockerCodeMisMatchException, PickupCodeExpiredException, PackPickupTimeExceededException {
		Optional<LockerPackage> lockerPackage = LockerPackageRepository.getLockerByLockerId(lockerId);
		if(!lockerPackage.isPresent()) {
			throw new LockerPackageNotFoundException("Locker Package is not found for :"+lockerId);
		}
		if(!lockerPackage.get().verifyCode(code)) {
			throw new LockerCodeMisMatchException("Codes doesn't match");
		}
		if(!lockerPackage.get().isValidCode(currDate, code)) {
			throw new PickupCodeExpiredException("Pick up code expired");
		}
		Locker locker = LockerRepository.lockerMap.get(lockerId);
		if(canPickFromLocker(lockerId, currDate)) {
			locker.setStatus(LockerStatus.AVAILABLE);
			lockerPackage.get().setCode(null);
		}else {
			lockerPackage.get().setCode(null);
			throw new PackPickupTimeExceededException("Locker location is closes");
		}
	}
	
	private boolean canPickFromLocker(String lockerId, LocalDateTime currDate) {
		Locker locker = LockerRepository.lockerMap.get(lockerId);
		LockerLocation location = LockerLocationRepository.getLockerLocation(locker.getLocationId());
		Timing timing = location.getTiming().getTimingMap().get(currDate.getDayOfWeek());
		Time currTime = Time.valueOf(currDate.getHour() + ":" + currDate.getMinute()+":"+currDate.getSecond());
		if(currTime.after(timing.getOpenTime()) && currTime.before(timing.getClosedTime())) {
			return true;
		}
		return false;
	}
	
}
