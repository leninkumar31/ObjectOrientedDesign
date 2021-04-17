package com.example.todo.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource {
	private String resource;
	private String field;
	private String code;
	private String message;

	public FieldErrorResource(String resource, String field, String code, String message) {
		super();
		this.resource = resource;
		this.field = field;
		this.code = code;
		this.message = message;
	}

	public String getResource() {
		return resource;
	}

	public String getField() {
		return field;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
