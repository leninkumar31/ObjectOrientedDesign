package com.leninkumar.amazonlocker.service;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.leninkumar.amazonlocker.exceptions.LockerCodeMisMatchException;
import com.leninkumar.amazonlocker.exceptions.LockerPackageNotFoundException;
import com.leninkumar.amazonlocker.exceptions.PackPickupTimeExceededException;
import com.leninkumar.amazonlocker.exceptions.PickupCodeExpiredException;
import com.leninkumar.amazonlocker.models.GeoLocation;
import com.leninkumar.amazonlocker.models.Item;
import com.leninkumar.amazonlocker.models.Locker;
import com.leninkumar.amazonlocker.models.LockerLocation;
import com.leninkumar.amazonlocker.models.LockerSize;
import com.leninkumar.amazonlocker.models.LockerStatus;
import com.leninkumar.amazonlocker.models.LockerTiming;
import com.leninkumar.amazonlocker.models.Notification;
import com.leninkumar.amazonlocker.models.Order;
import com.leninkumar.amazonlocker.models.Timing;
import com.leninkumar.amazonlocker.repository.LockerLocationRepository;
import com.leninkumar.amazonlocker.repository.LockerRepository;
import com.leninkumar.amazonlocker.repository.NotificationRepository;
import com.leninkumar.amazonlocker.repository.OrderRepository;

@RunWith(JUnit4.class)
public class LockerServiceTest {
	
	@Test
	public void testGetLocker() {
		Locker locker = new Locker("1", LockerSize.XS);
		LockerRepository.lockers.add(locker);
		LockerService lockerService = new LockerService();
		Locker newLocker = lockerService.getLocker(LockerSize.XS, new GeoLocation(1.0, 1.0));
		assertEquals(locker.getId(), newLocker.getId());
	}
	
	@Test
	public void testPickFromLocker() throws LockerPackageNotFoundException, LockerCodeMisMatchException, PickupCodeExpiredException, PackPickupTimeExceededException {
		LockerService lockerService = setUp();
		Notification notification = NotificationRepository.notificationMap.get("1");
		lockerService.pickFromLocker(notification.getLockerId(), notification.getCode(), LocalDateTime.now());
		assertEquals(LockerStatus.AVAILABLE, LockerRepository.lockerMap.get(notification.getLockerId()).getStatus());
	}
	
	public LockerService setUp() {
		OrderRepository.orderMap.put("1", getNewOrder("1"));
		addLockers();
		DeliveryService deliveryService = new DeliveryService();
		deliveryService.deliver("1");
		LockerService lockerService = new LockerService();
		return lockerService;
	}
	
	public void addLockers() {
		LockerTiming timing = getLockerTiming();
		LockerLocation location = new LockerLocation();
		location.setTiming(timing);
		location.setLocationId("1");
		LockerLocationRepository.lockerLocations.add(location);
		for(LockerSize size:LockerSize.values()) {
			Locker locker = new Locker("1", size);
			LockerRepository.lockers.add(locker);
			LockerRepository.lockerMap.put(locker.getId(), locker);
			location.getLockers().add(locker);
		}
	}
	
	public LockerTiming getLockerTiming() {
		LockerTiming timing = new LockerTiming();
		for(DayOfWeek day : DayOfWeek.values()) {
			timing.getTimingMap().put(day, new Timing("00:00:000", "23:59:000"));
		}
		return timing;
	}
	
	public Order getNewOrder(String orderId) {
		Order order = new Order();
		order.setOrderId(orderId);
		order.setDeliveryLocation(new GeoLocation(1.0, 1.0));
		order.setItems(getItems());
		return order;
	}
	
	public List<Item> getItems(){
		List<Item> list = new ArrayList<>();
		list.add(new Item("1", 1));
		return list;
	}
}
