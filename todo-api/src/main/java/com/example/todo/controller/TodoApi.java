package com.example.todo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.models.Todo;
import com.example.todo.security.JwtTokenUtil;
import com.example.todo.service.ITodoService;

@RestController
@Validated
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
	public ResponseEntity<?> insertTodo(@Valid @RequestBody Todo todo) {
		todoService.insertTodo(todo);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateTodo(@Valid @RequestBody Todo todo) {
		todoService.updateTodo(todo);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable @Min(value=1, message="Id must be greater than or equal to 1") Long id) {
		todoService.removeTodoById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
