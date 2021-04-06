package com.example.todo.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User {
	
	@NotEmpty(message = "User Name must not be empty")
	String UserName;
	
	@NotEmpty(message = "First Name must not be empty")
	String FirstName;
	
	@NotEmpty(message = "Last Name must not be empty")
	String LastName;
	
	@NotEmpty(message = "Email must not be empty")
	@Email(message = "Email must be a valid email address")
	String Email;
	
	@NotEmpty(message = "Password must not be empty")
	String Password;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
