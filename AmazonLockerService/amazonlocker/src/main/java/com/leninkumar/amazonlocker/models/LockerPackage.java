package com.leninkumar.amazonlocker.models;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LockerPackage {
	private final int validDays = 3;
	private String lockerId;
	private String orderId;
	private String code;
	private LocalDateTime deliveryDate;

	public boolean isValidCode(LocalDateTime currDate, String code) {
		if (currDate.compareTo(this.deliveryDate) > this.validDays) {
			return false;
		}
		return true;
	}

	public boolean verifyCode(String code) {
		return this.code.equals(code);
	}
}
