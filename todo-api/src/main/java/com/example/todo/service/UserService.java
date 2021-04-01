package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		UserDao dao = userRepository.validateUser(user.getUserName(), user.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + user.getUserName()));
		return convertUserDaoToUser(dao);
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
		userDao.setPassword(user.getPassword());
		return userDao;
	}
}
