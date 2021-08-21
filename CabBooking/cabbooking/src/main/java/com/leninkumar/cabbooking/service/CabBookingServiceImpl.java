package com.leninkumar.cabbooking.service;

import java.util.Optional;

import com.leninkumar.cabbooking.exceptions.DriversAreNotAvaiable;
import com.leninkumar.cabbooking.exceptions.UserDoesnotExistException;
import com.leninkumar.cabbooking.models.account.Driver;
import com.leninkumar.cabbooking.models.account.trip.Trip;
import com.leninkumar.cabbooking.models.account.trip.TripStatus;
import com.leninkumar.cabbooking.models.location.Address;
import com.leninkumar.cabbooking.models.vehicle.Vehicle;
import com.leninkumar.cabbooking.repository.DriverRepository;
import com.leninkumar.cabbooking.repository.TripRepository;
import com.leninkumar.cabbooking.repository.UserRepository;
import com.leninkumar.cabbooking.repository.VehicleRepository;

public class CabBookingServiceImpl implements CabBookingService {

	DriverService driverService;
	UserService userService;
	TripService tripService;

	public CabBookingServiceImpl(DriverService driverService, TripService tripService, UserService userService) {
		this.driverService = driverService;
		this.tripService = tripService;
		this.userService = userService;
	}

	@Override
	public Trip bookTrip(String userName, Address pickUpLocation, Address dropLocation)
			throws UserDoesnotExistException, DriversAreNotAvaiable {
		if (!UserRepository.verifyUser(userName)) {
			throw new UserDoesnotExistException("Invalid userName: " + userName);
		}
		Optional<Driver> driver = DriverRepository.findDriver(pickUpLocation.getLocation());
		if (!driver.isPresent()) {
			throw new DriversAreNotAvaiable("Couldn't find drivers in your location");
		}
		Vehicle vehicle = VehicleRepository.getVehicle(driver.get().getUserName());
		Trip trip = this.tripService.createTrip(userName, vehicle, pickUpLocation, dropLocation);
		driverService.informDriver(trip);
		TripRepository.addTrip(trip);
		return trip;
	}

	@Override
	public void endTrip(Trip trip) {
		trip.setStatus(TripStatus.ENDED);
		driverService.informDriver(trip);
	}

	@Override
	public void cancelTrip(Trip trip) {
		trip.setStatus(TripStatus.CANCELLED);
		driverService.informDriver(trip);
	}

}
