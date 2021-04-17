package com.example.todo.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateTodoParam extends TodoParam {
	@NotNull(message = "Id must not be empty")
	@Min(value = 1, message = "Id must be greater than or equal to 1")
	Long Id;

	public UpdateTodoParam(
			@NotNull(message = "Id must not be empty") @Min(value = 1, message = "Id must be greater than or equal to 1") Long id,
			@NotEmpty(message = "Task must not be empty") String task, boolean isCompleted) {
		super(task, isCompleted);
		Id = id;
	}

	public Long getId() {
		return Id;
	}
	
}
