package com.leninkumar.cabbooking.models.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Review {
	private double rating;
	private String comment;
}
