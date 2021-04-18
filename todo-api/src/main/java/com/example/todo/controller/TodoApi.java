package com.example.todo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.models.Todo;
import com.example.todo.models.TodoParam;
import com.example.todo.models.UserDetails;
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
	public List<Todo> readTodo(@AuthenticationPrincipal UserDetails user,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "1") Integer size) {
		return todoService.fetchPaginatedTodoListByUserName(user.getUserName(), page, size);
	}

	@PostMapping("/insert")
	public ResponseEntity<?> insertTodo(@AuthenticationPrincipal UserDetails user, @Valid @RequestBody TodoParam todo) {
		todoService.insertTodo(user, todo);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTodo(
			@PathVariable @Min(value = 1, message = "Id must be greater than or equal to 1") Long id,
			@Valid @RequestBody Todo todo) {
		todoService.updateTodo(id, todo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTodo(
			@PathVariable @Min(value = 1, message = "Id must be greater than or equal to 1") Long id) {
		todoService.removeTodoById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
