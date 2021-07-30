package com.leninkumar.amazonlocker.models;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Locker {
	private String id;
	private LockerSize size;
	private LockerStatus status;
	private String locationId;

	public Locker(String locationId, LockerSize size) {
		this.id = UUID.randomUUID().toString();
		this.locationId = locationId;
		this.size = size;
		this.status = LockerStatus.AVAILABLE;
	}
}
