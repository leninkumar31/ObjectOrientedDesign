package com.example.todo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.entities.UserDao;
import com.example.todo.models.User;
import com.example.todo.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User insertUser(User user) {
		UserDao dao = userRepository.save(convertUserToUserDao(user));
		return convertUserDaoToUser(Optional.ofNullable(dao));
	}

	@Override
	public Optional<User> getUser(String userName) {
		Optional<UserDao> userDao = userRepository.getUserByUserName(userName);
		return Optional.ofNullable(convertUserDaoToUser(userDao));
	}

	private User convertUserDaoToUser(Optional<UserDao> userDao) {
		if(!userDao.isPresent()) {
			return null;
		}
		User user = new User();
		user.setUserName(userDao.get().getUserName());
		user.setEmail(userDao.get().getEmail());
		user.setFirstName(userDao.get().getFirstName());
		user.setLastName(userDao.get().getLastName());
		user.setPassword(userDao.get().getPassword());
		return user;
	}
	
	private UserDao convertUserToUserDao(User user) {
		if(user == null) {
			return null;
		}
		UserDao userDao = new UserDao();
		userDao.setUserName(user.getUserName());
		userDao.setEmail(user.getEmail());
		userDao.setFirstName(user.getFirstName());
		userDao.setLastName(user.getLastName());
		userDao.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao;
	}
}
