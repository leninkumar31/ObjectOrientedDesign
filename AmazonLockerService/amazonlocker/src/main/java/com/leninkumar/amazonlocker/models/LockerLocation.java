package com.leninkumar.amazonlocker.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LockerLocation {
	private String locationId;
	private List<Locker> lockers = new ArrayList<>();
	private GeoLocation location;
	private LockerTiming timing;
}
