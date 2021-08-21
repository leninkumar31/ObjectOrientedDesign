package com.leninkumar.cabbooking.service;

import com.leninkumar.cabbooking.models.account.trip.Trip;

public class NotificationServiceImpl implements NotificationService{

	@Override
	public void sendNotification(Trip trip, String message) {
		System.out.println(message);
	}

}
