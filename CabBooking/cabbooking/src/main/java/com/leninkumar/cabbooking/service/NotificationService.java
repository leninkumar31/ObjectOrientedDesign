package com.leninkumar.cabbooking.service;

import com.leninkumar.cabbooking.models.account.trip.Trip;

public interface NotificationService {
	void sendNotification(Trip trip, String message);
}
