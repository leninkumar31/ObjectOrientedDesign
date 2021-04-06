package com.example.todo.models;

import javax.validation.constraints.NotEmpty;

public class Login {
	
	@NotEmpty(message = "User name must not be empty")
	String UserName;
	
	@NotEmpty(message = "Password must not be empty")
	String Password;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
