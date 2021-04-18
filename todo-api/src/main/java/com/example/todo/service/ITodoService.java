package com.example.todo.service;

import java.util.List;

import com.example.todo.models.TodoParam;
import com.example.todo.models.Todo;
import com.example.todo.models.UpdateTodoParam;
import com.example.todo.models.UserDetails;

public interface ITodoService {
	List<Todo> fetchTodoListByUserName(String userName);

	Todo insertTodo(UserDetails user, TodoParam todoParam);
	
	void updateTodo(UpdateTodoParam todoParam);

	void removeTodoById(Long id);
}
