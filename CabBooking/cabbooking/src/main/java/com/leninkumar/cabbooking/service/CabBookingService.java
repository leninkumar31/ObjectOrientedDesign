package com.leninkumar.cabbooking.service;

import com.leninkumar.cabbooking.exceptions.DriversAreNotAvaiable;
import com.leninkumar.cabbooking.exceptions.UserDoesnotExistException;
import com.leninkumar.cabbooking.models.account.trip.Trip;
import com.leninkumar.cabbooking.models.location.Address;

public interface CabBookingService {
	Trip bookTrip(String userName, Address pickUpLocation, Address dropLocation)
			throws UserDoesnotExistException, DriversAreNotAvaiable;

	void endTrip(Trip trip);

	void cancelTrip(Trip trip);
}
