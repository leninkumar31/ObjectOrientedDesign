package com.leninkumar.cabbooking.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leninkumar.cabbooking.exceptions.InCorrectPasswordException;
import com.leninkumar.cabbooking.exceptions.UserDoesnotExistException;
import com.leninkumar.cabbooking.models.account.User;

public class UserRepository {
	public static List<User> userList = new ArrayList<>();
	private static Map<String, User> userMap = new HashMap<>();
	
	public static void addUser(User user) {
		userList.add(user);
		userMap.put(user.getUserName(), user);
	}
	
	public static boolean verifyUser(String userName){
		if(!userMap.containsKey(userName)) {
			return false;
		}
		return true;
	}
	
	public static boolean verifyUserNameAndPassword(String userName, String password) throws UserDoesnotExistException, InCorrectPasswordException{
		if(!userMap.containsKey(userName)) {
			throw new UserDoesnotExistException("User doesn't exists: "+userName);
		}
		if(!userMap.get(userName).getPassword().equals(password)) {
			throw new InCorrectPasswordException("Given Password is not correct");
		}
		return true;
	}
}
