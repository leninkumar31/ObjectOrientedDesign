package com.leninkumar.cabbooking.models.location;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String streetName;
	private String city;
	private String state;
	private String country;
	private String pin;
	private GeoLocation location;
}
