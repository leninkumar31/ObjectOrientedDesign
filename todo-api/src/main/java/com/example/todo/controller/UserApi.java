package com.example.todo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.exceptions.InvalidAuthenticationException;
import com.example.todo.exceptions.UserNameAlreadyExistsException;
import com.example.todo.models.Login;
import com.example.todo.models.User;
import com.example.todo.models.UserDetails;
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

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		Optional<UserDetails> userData = userService.getUser(user.getUserName());
		if (userData.isPresent()) {
			throw new UserNameAlreadyExistsException(
					"UserName alredy exists. Please Use Some other name ::" + user.getUserName());
		}
		UserDetails data = userService.insertUser(user);
		String token = jwtService.generateToken(data);
		return getResponseEntityWithHeaders(token, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody Login user) {
		Optional<UserDetails> userData = userService.getUser(user.getUserName());
		if (!userData.isPresent() || !passwordEncoder.matches(user.getPassword(), userData.get().getPassword())) {
			throw new InvalidAuthenticationException("Incorrect Username or Password");
		}
		String token = jwtService.generateToken(userData.get());
		return getResponseEntityWithHeaders(token, HttpStatus.OK);
	}

	private ResponseEntity<?> getResponseEntityWithHeaders(String token, HttpStatus status) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		return new ResponseEntity<>(headers, status);
	}

}
