package com.example.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.entities.TodoDao;
import com.example.todo.entities.UserDao;
import com.example.todo.models.Todo;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoService implements ITodoService {

	@Autowired
	TodoRepository todoRepository;

	@Override
	public List<Todo> fetchTodoListByUserId(Long id) {
		List<TodoDao> daoList = todoRepository.findByUser_UserId(id);
		List<Todo> todoList = new ArrayList<>();
		for (TodoDao dao : daoList) {
			todoList.add(convertDaoToTodo(dao));
		}
		return todoList;
	}

	@Override
	public Todo insertTodo(Todo todo) {
		return convertDaoToTodo(todoRepository.save(convertTodoToDao(todo)));
	}

	@Override
	public Todo updateTodo(Todo todo) {
		TodoDao dao = convertTodoToDao(todo);
		dao.setId(todo.getId());
		return convertDaoToTodo(todoRepository.save(dao));
	}

	private TodoDao convertTodoToDao(Todo todo) {
		UserDao user = new UserDao();
		user.setUserId(todo.getUserId());
		TodoDao dao = new TodoDao();
		dao.setUser(user);
		dao.setTask(todo.getTask());
		dao.setIsCompleted(todo.isCompleted());
		dao.setCreatedDate(todo.getCreatedDate());
		return dao;
	}

	private Todo convertDaoToTodo(TodoDao dao) {
		Todo todo = new Todo();
		todo.setId(dao.getId());
		todo.setUserId(dao.getUser().getUserId());
		todo.setTask(dao.getTask());
		todo.setCompleted(dao.getIsCompleted());
		todo.setCreatedDate(dao.getCreatedDate());
		return todo;
	}

	@Override
	public void removeTodoById(Long id) {
		todoRepository.deleteById(id);
	}

}
