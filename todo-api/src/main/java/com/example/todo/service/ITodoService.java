package com.example.todo.service;

import java.util.List;

import com.example.todo.models.Todo;

public interface ITodoService {
	List<Todo> fetchTodoListByUserId(Long id);

	Todo insertTodo(Todo todo);
	
	Todo updateTodo(Todo todo);

	void removeTodoById(Long id);
}
