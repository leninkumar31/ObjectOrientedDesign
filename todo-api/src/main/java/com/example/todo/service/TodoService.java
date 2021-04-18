package com.example.todo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.todo.entities.TodoDao;
import com.example.todo.entities.UserDao;
import com.example.todo.models.Todo;
import com.example.todo.models.TodoParam;
import com.example.todo.models.UserDetails;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoService implements ITodoService {

	@Autowired
	TodoRepository todoRepository;

	@Override
	public Todo fetchTodoById(Long id) {
		Optional<TodoDao> todoDao = todoRepository.findById(id);
		if (todoDao.isPresent()) {
			return convertDaoToTodo(todoDao.get());
		}
		return new Todo();
	}

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
	public List<Todo> fetchPaginatedTodoListByUserName(String userName, Integer pageNum, Integer pageSize) {
		PageRequest paging = PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
		Page<TodoDao> pagedResult = todoRepository.findByUser_UserName(userName, paging);
		if (pagedResult.hasContent()) {
			List<Todo> todoList = new ArrayList<>();
			for (TodoDao dao : pagedResult.getContent()) {
				todoList.add(convertDaoToTodo(dao));
			}
			return todoList;
		}
		return new ArrayList<>();
	}

	@Override
	public Todo insertTodo(UserDetails user, TodoParam todoParam) {
		return convertDaoToTodo(todoRepository.save(convertTodoToDao(user, todoParam)));
	}

	@Override
	public void updateTodo(Long id, Todo todo) {
		todoRepository.updateTodo(id, todo.getTask(), todo.isCompleted(), new Date());
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
		dao.setCreatedDate(currTime);
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
