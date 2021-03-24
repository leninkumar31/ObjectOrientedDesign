package com.udemy.spring.basics.springdemo.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SomeExternalService {
	
	@Value("${external.service.url}")
	private String url;

	public String getUrl() {
		return url;
	}
}
