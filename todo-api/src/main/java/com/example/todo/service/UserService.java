package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.entities.UserDao;
import com.example.todo.exceptions.ResourceAlreadyExistsException;
import com.example.todo.exceptions.ResourceNotFoundException;
import com.example.todo.models.User;
import com.example.todo.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User insertUser(User user) throws ResourceAlreadyExistsException {
		if (userRepository.validateUserName(user.getUserName()).isPresent()) {
			throw new ResourceAlreadyExistsException(
					"UserName alredy exists. Please Use Some other name ::" + user.getUserName());
		}
		UserDao dao = userRepository.save(convertUserToUserDao(user));
		return convertUserDaoToUser(dao);
	}

	@Override
	public User validateUser(User user) throws ResourceNotFoundException {
		UserDao userDao = userRepository.validateUserName(user.getUserName())
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + user.getUserName()));
		if (!passwordEncoder.matches(user.getPassword(), userDao.getPassword())) {
			throw new ResourceNotFoundException("Incorrect Password for:: " + user.getUserName());
		}
		return convertUserDaoToUser(userDao);
	}

	private User convertUserDaoToUser(UserDao userDao) {
		User user = new User();
		user.setUserId(userDao.getUserId());
		user.setUserName(userDao.getUserName());
		user.setEmail(userDao.getEmail());
		user.setFirstName(userDao.getFirstName());
		user.setLastName(userDao.getLastName());
		return user;
	}

	private UserDao convertUserToUserDao(User user) {
		UserDao userDao = new UserDao();
		userDao.setUserName(user.getUserName());
		userDao.setEmail(user.getEmail());
		userDao.setFirstName(user.getFirstName());
		userDao.setLastName(user.getLastName());
		userDao.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao;
	}
}
