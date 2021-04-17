package com.example.todo.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidAuthenticationException.class)
	public ResponseEntity<Object> handleInvalidAuthentication(InvalidAuthenticationException e, WebRequest request) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<String, Object>() {
			{
				put("message", e.getMessage());
			}
		});
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldErrorResource> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fieldError -> new FieldErrorResource(
						fieldError.getObjectName(),
						fieldError.getField(),
						fieldError.getCode(),
						fieldError.getDefaultMessage()
						))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResource(errors));
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResource handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<FieldErrorResource> errors = new ArrayList<>();
		for(ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			FieldErrorResource fieldErrorResource = new FieldErrorResource(
					violation.getRootBeanClass().getName(), 
					violation.getPropertyPath().toString(), 
					violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName(),
					violation.getMessage());
			errors.add(fieldErrorResource);
		}
		return new ErrorResource(errors);
	}
}
