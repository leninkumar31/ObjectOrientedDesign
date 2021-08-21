package com.leninkumar.cabbooking.models.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver extends Account{
	private LicenceInfo licenceInfo;
	private DriverStatus driverStatus;
	public Driver(String userName, String password, String phoneNum) {
		super(userName, password, phoneNum);
	}

}
