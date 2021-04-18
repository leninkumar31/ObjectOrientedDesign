package com.example.todo.service;

import java.util.List;

import com.example.todo.models.Todo;
import com.example.todo.models.TodoParam;
import com.example.todo.models.UserDetails;

public interface ITodoService {
	List<Todo> fetchTodoListByUserName(String userName);

	List<Todo> fetchPaginatedTodoListByUserName(String userName, Integer pageNum, Integer pageSize);

	Todo insertTodo(UserDetails user, TodoParam todoParam);

	void updateTodo(Long id, Todo todo);

	void removeTodoById(Long id);
}
