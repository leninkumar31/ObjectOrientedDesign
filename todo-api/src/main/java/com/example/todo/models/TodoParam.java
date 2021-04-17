package com.example.todo.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TodoParam {
	@NotEmpty(message = "Task must not be empty")
	String task;

	@NotNull(message = "isCompleted must be either true or false")
	boolean isCompleted;

	public TodoParam() {
	}

	public TodoParam(@NotEmpty(message = "Task must not be empty") String task, boolean isCompleted) {
		this.task = task;
		this.isCompleted = isCompleted;
	}

	public String getTask() {
		return task;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

}
