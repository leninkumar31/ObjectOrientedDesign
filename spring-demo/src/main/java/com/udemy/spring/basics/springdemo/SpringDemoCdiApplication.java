package com.udemy.spring.basics.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.udemy.spring.basics.springdemo.cdi.SomeCdiBusiness;

@Configuration
@ComponentScan
public class SpringDemoCdiApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(SpringDemoCdiApplication.class);

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				SpringDemoBasicApplication.class)) {
			SomeCdiBusiness someCdiBusiness = context.getBean(SomeCdiBusiness.class);

			LOGGER.info("{}", someCdiBusiness);
		}
	}

}
