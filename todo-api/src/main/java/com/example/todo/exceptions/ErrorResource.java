package com.example.todo.exceptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ErrorResourceSerializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("errors")
public class ErrorResource {
	private List<FieldErrorResource> fieldErrors;
	
	public ErrorResource(List<FieldErrorResource> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public List<FieldErrorResource> getFieldErrors() {
		return fieldErrors;
	}
}
