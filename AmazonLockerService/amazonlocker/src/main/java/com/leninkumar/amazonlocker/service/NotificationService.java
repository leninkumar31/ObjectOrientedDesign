package com.leninkumar.amazonlocker.service;

import com.leninkumar.amazonlocker.models.LockerPackage;
import com.leninkumar.amazonlocker.models.Notification;
import com.leninkumar.amazonlocker.repository.NotificationRepository;

public class NotificationService {
	CustomerService customerService = new CustomerService();
	public void notifyCustomer(LockerPackage pack){
		String customerId = customerService.getCustomerIdFromOrder(pack.getOrderId());
		Notification notification = new Notification(customerId, pack.getOrderId(), pack.getLockerId(), pack.getCode());
		NotificationRepository.notificationMap.put(pack.getOrderId(), notification);
		System.out.println(notification);
	}
}
