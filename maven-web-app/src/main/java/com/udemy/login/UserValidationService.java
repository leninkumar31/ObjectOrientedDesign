package com.udemy.login;

import org.springframework.stereotype.Service;

@Service
public class UserValidationService {
	
	public boolean isUserValid(String name, String password) {
		if("lenin".equals(name) && "lenin".equals(password)) {
			return true;
		}
		return false;
	}

}
