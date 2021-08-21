package com.leninkumar.cabbooking.service;

import com.leninkumar.cabbooking.models.account.Account;

public interface AccountService {
	public Account registerAccount();
	public void logOut();
	boolean logIn(String userName, String password);
}
