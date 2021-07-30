package com.leninkumar.amazonlocker.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.leninkumar.amazonlocker.models.GeoLocation;
import com.leninkumar.amazonlocker.models.Item;
import com.leninkumar.amazonlocker.models.Locker;
import com.leninkumar.amazonlocker.models.LockerSize;
import com.leninkumar.amazonlocker.models.Order;
import com.leninkumar.amazonlocker.repository.LockerRepository;
import com.leninkumar.amazonlocker.repository.NotificationRepository;
import com.leninkumar.amazonlocker.repository.OrderRepository;

@RunWith(JUnit4.class)
public class DeliveryServiceTest {
	
	public DeliveryService setUp() {
		OrderRepository.orderMap.put("1", getNewOrder("1"));
		addLockers();
		DeliveryService deliveryService = new DeliveryService();
		return deliveryService;
	}
	
	@Test
	public void testDeliver() {
		DeliveryService service = setUp();
		service.deliver("1");
		assertEquals(1, NotificationRepository.notificationMap.size());
	}
	
	public void addLockers() {
		for(LockerSize size:LockerSize.values()) {
			Locker locker = new Locker("1", size);
			LockerRepository.lockers.add(locker);
			LockerRepository.lockerMap.put(locker.getId(), locker);
		}
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
