package com.leninkumar.cabbooking.models.location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoLocation {
	public final double maxDist = 5;
	private double lattitude;
	private double longitude;
	
	public GeoLocation(double lattitude, double longitude) {
		this.lattitude = lattitude;
		this.longitude = longitude;
	}
	
	public boolean isWithInRange(GeoLocation driverLoc) {
		return Math.sqrt(Math.pow(this.getLattitude() - driverLoc.getLattitude(), 2)
				+ Math.pow(this.getLattitude() - driverLoc.getLattitude(), 2)) <= maxDist;
	}
	
	public double getDistance(GeoLocation driverLoc) {
		return Math.sqrt(Math.pow(this.getLattitude() - driverLoc.getLattitude(), 2)
				+ Math.pow(this.getLattitude() - driverLoc.getLattitude(), 2));
	}
}
