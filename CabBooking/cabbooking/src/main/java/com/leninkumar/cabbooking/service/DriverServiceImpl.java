package com.leninkumar.cabbooking.service;

import com.leninkumar.cabbooking.models.account.Driver;
import com.leninkumar.cabbooking.models.account.DriverStatus;
import com.leninkumar.cabbooking.models.account.trip.Trip;
import com.leninkumar.cabbooking.repository.DriverRepository;

public class DriverServiceImpl implements DriverService{

	
	public DriverServiceImpl() {
	}

	@Override
	public void changeAvailability(String driverName, DriverStatus status) {
		Driver driver = DriverRepository.driverMap.get(driverName);
		driver.setDriverStatus(status);
	}

	@Override
	public void informDriver(Trip trip) {
		String driverName = trip.getVehicle().getDriver();
		switch(trip.getStatus()) {
		case BOOKED:
			changeAvailability(driverName, DriverStatus.BOOKED);
			break;
		case ENDED:
		case CANCELLED:
			changeAvailability(driverName, DriverStatus.AVAILABLE);
			break;
		default:
			System.out.println("No need to do anything");
		}
	}

}
