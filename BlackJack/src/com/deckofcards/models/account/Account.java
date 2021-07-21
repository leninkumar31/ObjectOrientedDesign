package com.deckofcards.models.account;

import java.time.LocalDate;

import com.deckofcards.models.card.Card;
import com.deckofcards.models.hand.Hand;

public abstract class Account <T extends Card> {
	String id;
	String email;
	String userName;
	String passWord;
	LocalDate lastAccessed;
	Contact contact;
	PersonalInfo personalInfo;
	AccountStatus status;
	private int totalCash;
	private Hand<T> hand;
	
	public Hand<T> getHand() {
		return hand;
	}

	public void setHand(Hand<T> hand) {
		this.hand = hand;
	}
	
	public int getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(int totalCash) {
		this.totalCash = totalCash;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public LocalDate getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(LocalDate lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

}
