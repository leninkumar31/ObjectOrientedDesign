package com.example.todo.exceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ErrorResourceSerializer extends JsonSerializer<ErrorResource>{

	@Override
	public void serialize(ErrorResource value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		Map<String, List<String>> errorMap = new HashMap<>();
		gen.writeStartObject();
		gen.writeObjectFieldStart("errors");
		for(FieldErrorResource fieldError : value.getFieldErrors()) {
			if(!errorMap.containsKey(fieldError.getField())) {
				errorMap.put(fieldError.getField(), new ArrayList<>());
			}
			errorMap.get(fieldError.getField()).add(fieldError.getMessage());
		}
		for(Map.Entry<String, List<String>> entry : errorMap.entrySet()) {
			gen.writeArrayFieldStart(entry.getKey());
			entry.getValue().forEach( errorMessage -> {
				try {
					gen.writeString(errorMessage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			gen.writeEndArray();
		}
		gen.writeEndObject();
		gen.writeEndObject();
	}

}
