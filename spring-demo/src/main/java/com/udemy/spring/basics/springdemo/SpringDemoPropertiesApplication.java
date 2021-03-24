package com.udemy.spring.basics.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.udemy.spring.basics.springdemo.properties.SomeExternalService;

@Configuration
@ComponentScan
@PropertySource("classpath:app.properties")
public class SpringDemoPropertiesApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(SpringDemoCdiApplication.class);
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			
//			LOGGER.info("Beans loaded -> {}", (Object)context.getBeanDefinitionNames());
			SomeExternalService service = context.getBean(SomeExternalService.class);
			LOGGER.info("{}", service);
			LOGGER.info("{}",service.getUrl());
		}
	}

}