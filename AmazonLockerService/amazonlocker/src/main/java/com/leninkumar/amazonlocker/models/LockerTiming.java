package com.leninkumar.amazonlocker.models;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LockerTiming {

	Map<DayOfWeek, Timing> timingMap;

	public LockerTiming() {
		timingMap = new HashMap<>();
	}

}
