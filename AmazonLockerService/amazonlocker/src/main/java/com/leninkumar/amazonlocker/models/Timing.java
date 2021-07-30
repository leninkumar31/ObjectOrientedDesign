package com.leninkumar.amazonlocker.models;

import java.sql.Time;

import lombok.Getter;

@Getter
public class Timing {
	Time openTime, closedTime;
	public Timing(String openTime, String closedTime) {
		this.openTime = Time.valueOf(openTime);
		this.closedTime = Time.valueOf(closedTime);
	}
}
