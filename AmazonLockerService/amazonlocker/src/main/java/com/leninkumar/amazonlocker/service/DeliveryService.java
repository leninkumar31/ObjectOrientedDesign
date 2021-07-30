package com.leninkumar.amazonlocker.service;

import java.time.LocalDateTime;
import java.util.List;

import com.leninkumar.amazonlocker.exceptions.PackageSizeMappingException;
import com.leninkumar.amazonlocker.models.Item;
import com.leninkumar.amazonlocker.models.Locker;
import com.leninkumar.amazonlocker.models.LockerPackage;
import com.leninkumar.amazonlocker.models.LockerSize;
import com.leninkumar.amazonlocker.models.LockerStatus;
import com.leninkumar.amazonlocker.models.Order;
import com.leninkumar.amazonlocker.models.Package;
import com.leninkumar.amazonlocker.repository.LockerPackageRepository;
import com.leninkumar.amazonlocker.utils.Utils;

public class DeliveryService {

	OrdersService orderService = new OrdersService();
	LockerService lockerService = new LockerService();
	NotificationService notificationService = new NotificationService();

	public void deliver(String orderId) {
		Order order = orderService.getOrder(orderId);
		List<Item> items = order.getItems();
		Package pack = new Package(orderId, items);
		LockerSize size;
		try {
			size = Utils.getLockerSizeFromPackageSize(pack.getPackageSize());
		} catch (PackageSizeMappingException e) {
			System.out.println("Couldn't find proper locker size for the given package size");
			return;
		}
		Locker locker = lockerService.getLocker(size, order.getDeliveryLocation());
		LockerPackage lockerPackage = new LockerPackage();
		lockerPackage.setOrderId(orderId);
		lockerPackage.setLockerId(locker.getId());
		lockerPackage.setDeliveryDate(LocalDateTime.now());
		lockerPackage.setCode(Utils.generateRandomCode(6));
		LockerPackageRepository.lockerPackages.add(lockerPackage);
		locker.setStatus(LockerStatus.PLACED);
		notificationService.notifyCustomer(lockerPackage);
	}
}
