package com.leninkumar.cabbooking.models.account;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LicenceInfo {
	private String licenceNumber;
	private String nameOnLicence;
	private LocalDate issuedAt;
	private LocalDate expiredAt;
	private LicenceType licenceType;
}
