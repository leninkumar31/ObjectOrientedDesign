package com.leninkumar.cabbooking.service;

import com.leninkumar.cabbooking.models.account.DriverStatus;
import com.leninkumar.cabbooking.models.account.trip.Trip;

public interface DriverService {
	public void informDriver(Trip trip);
	public void changeAvailability(String driver, DriverStatus status);
}
