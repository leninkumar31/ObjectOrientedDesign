package com.leninkumar.cabbooking.models.account;

import com.leninkumar.cabbooking.models.location.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
	private String email;
	private String phoneNumber;
	private Address address;
}
