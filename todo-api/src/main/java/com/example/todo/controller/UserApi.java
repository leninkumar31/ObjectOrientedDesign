package com.example.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.exceptions.ResourceAlreadyExistsException;
import com.example.todo.exceptions.ResourceNotFoundException;
import com.example.todo.models.Login;
import com.example.todo.models.Todo;
import com.example.todo.models.User;
import com.example.todo.security.JwtTokenUtil;
import com.example.todo.service.ITodoService;
import com.example.todo.service.IUserService;

@RestController
public class UserApi {

	@Autowired
	IUserService userService;

	@Autowired
	ITodoService todoService;

	@Autowired
	JwtTokenUtil jwtService;

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) throws ResourceAlreadyExistsException {
		User data = userService.insertUser(user);
		String token = jwtService.generateToken(data);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<List<Todo>> loginUser(@Valid @RequestBody Login user) throws ResourceNotFoundException {
		User data = userService.validateUser(user);
		String token = jwtService.generateToken(data);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

}
