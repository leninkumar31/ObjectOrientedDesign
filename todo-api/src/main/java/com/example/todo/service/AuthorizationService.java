package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.models.Todo;
import com.example.todo.models.UserDetails;

@Service
public class AuthorizationService  {
	
	@Autowired
	ITodoService todoService;

	public Boolean canUpdate(UserDetails user, Long id) {
		Todo todo = todoService.fetchTodoById(id);
		return user.getUserId().equals(todo.getUserId());
	}
}
