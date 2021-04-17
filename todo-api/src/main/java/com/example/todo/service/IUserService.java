package com.example.todo.service;

import java.util.Optional;

import com.example.todo.models.User;
import com.example.todo.models.UserDetails;

public interface IUserService {

	UserDetails insertUser(User user);

	Optional<UserDetails> getUser(String userName);
}
