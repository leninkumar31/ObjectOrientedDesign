package com.example.todo.service;

import com.example.todo.exceptions.ResourceAlreadyExistsException;
import com.example.todo.exceptions.ResourceNotFoundException;
import com.example.todo.models.Login;
import com.example.todo.models.User;

public interface IUserService {

	User insertUser(User user) throws ResourceAlreadyExistsException;

	User validateUser(Login user) throws ResourceNotFoundException;
}
