package com.leninkumar.cabbooking.models.account;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalInfo {
	private String firstName;
	private String lastName;
	private String middleName;
	private LocalDate dob;
}
