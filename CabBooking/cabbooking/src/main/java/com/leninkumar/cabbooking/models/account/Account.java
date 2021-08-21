package com.leninkumar.cabbooking.models.account;

import java.util.ArrayList;
import java.util.List;

import com.leninkumar.cabbooking.models.location.GeoLocation;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Account {
	private final String phoneNumber;
	@Setter
	private String password;
	private final String userName;
	@Setter
	private double rating;
	private List<Review> reviews;

	@Setter
	private Contact contact;
	
	@Setter
	private GeoLocation location;

	@Setter
	private PersonalInfo personalInfo;

	public Account(String userName, String password, String phoneNum) {
		this.userName = userName;
		this.phoneNumber = phoneNum;
		this.password = password;
		this.reviews = new ArrayList<>();
		this.rating = 5.0;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
		this.rating = (review.getRating() + (this.reviews.size() - 1) * this.rating) / this.reviews.size();
	}
	
	public void resetPassword(String newPass) {
		this.password = newPass;
	}
}
