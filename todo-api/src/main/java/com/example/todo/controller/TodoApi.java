package com.example.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.models.Todo;
import com.example.todo.service.ITodoService;

@RestController
public class TodoApi {

	@Autowired
	ITodoService todoService;

	@PostMapping("/insert")
	public List<Todo> insertTodo(@RequestBody Todo todo) {
		todoService.insertTodo(todo);
		return todoService.fetchTodoListByUserId(todo.getUserId());
	}

	@PostMapping("/update")
	public List<Todo> updateTodo(@RequestBody Todo todo) {
		todoService.updateTodo(todo);
		return todoService.fetchTodoListByUserId(todo.getUserId());
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteTodo(@RequestBody Todo todo) {
		todoService.removeTodoById(todo.getId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
