package com.udemy.spring.basics.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.udemy.spring.basics.componentscan.ComponentDAO;

@Configuration
@ComponentScan("com.udemy.spring.basics.componentscan")
public class SpringDemoScopeApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(SpringDemoScopeApplication.class);

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				SpringDemoBasicApplication.class)) {
			ComponentDAO componentDAO = context.getBean(ComponentDAO.class);

			LOGGER.info("{}", componentDAO);
		}
	}

}
