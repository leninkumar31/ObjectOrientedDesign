package com.example.todo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.entities.TodoDao;
import com.example.todo.entities.UserDao;
import com.example.todo.models.Todo;
import com.example.todo.models.TodoParam;
import com.example.todo.models.UpdateTodoParam;
import com.example.todo.models.UserDetails;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoService implements ITodoService {

	@Autowired
	TodoRepository todoRepository;

	@Override
	public List<Todo> fetchTodoListByUserName(String userName) {
		List<TodoDao> daoList = todoRepository.findByUser_UserName(userName);
		List<Todo> todoList = new ArrayList<>();
		for (TodoDao dao : daoList) {
			todoList.add(convertDaoToTodo(dao));
		}
		return todoList;
	}

	@Override
	public Todo insertTodo(UserDetails user, TodoParam todoParam) {
		return convertDaoToTodo(todoRepository.save(convertTodoToDao(user, todoParam)));
	}

	@Override
	public void updateTodo(UpdateTodoParam todoParam) {
		todoRepository.updateTodo(todoParam.getId(), todoParam.getTask(), todoParam.isCompleted());
	}

	@Override
	public void removeTodoById(Long id) {
		todoRepository.deleteById(id);
	}

	private TodoDao convertTodoToDao(UserDetails user, TodoParam todoParam) {
		UserDao userDao = new UserDao();
		userDao.setUserId(user.getUserId());
		TodoDao dao = new TodoDao();
		dao.setUser(userDao);
		dao.setTask(todoParam.getTask());
		dao.setIsCompleted(todoParam.isCompleted());
		Date currTime = new Date();
		if (!(todoParam instanceof UpdateTodoParam)) {
			dao.setCreatedDate(currTime);
		}
		dao.setModifiedDate(currTime);
		return dao;
	}

	private Todo convertDaoToTodo(TodoDao dao) {
		Todo todo = new Todo();
		todo.setId(dao.getId());
		todo.setUserId(dao.getUser().getUserId());
		todo.setTask(dao.getTask());
		todo.setCompleted(dao.getIsCompleted());
		todo.setCreatedDate(dao.getCreatedDate());
		todo.setModifiedDate(dao.getModifiedDate());
		return todo;
	}
}
