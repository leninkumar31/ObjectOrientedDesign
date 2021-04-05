package com.example.todo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.models.Todo;
import com.example.todo.security.JwtTokenUtil;
import com.example.todo.service.ITodoService;

@RestController
public class TodoApi {

	@Autowired
	ITodoService todoService;
	
	@Autowired
	JwtTokenUtil jwtService;

	@GetMapping("/")
	public List<Todo> readTodo(@RequestHeader("Authorization") String token) {
		Optional<String> userName = jwtService.getUserNameFromToken(token.split(" ")[1]);
		return todoService.fetchTodoListByUserName(userName.get());
	}

	@PostMapping("/insert")
	public ResponseEntity<?> insertTodo(@RequestBody Todo todo) {
		todoService.insertTodo(todo);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateTodo(@RequestBody Todo todo) {
		todoService.updateTodo(todo);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteTodo(@RequestBody Todo todo) {
		todoService.removeTodoById(todo.getId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
