package com.example.todo.service;

import java.util.Optional;

import com.example.todo.models.User;

public interface IUserService {

	User insertUser(User user);

	Optional<User> getUser(String userName);
}
