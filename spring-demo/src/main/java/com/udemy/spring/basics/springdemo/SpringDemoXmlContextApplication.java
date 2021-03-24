package com.udemy.spring.basics.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.udemy.spring.basics.springdemo.xml.XmlPersonDAO;

public class SpringDemoXmlContextApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(SpringDemoCdiApplication.class);
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			
			LOGGER.info("Beans loaded -> {}", (Object)context.getBeanDefinitionNames());
			XmlPersonDAO person = context.getBean(XmlPersonDAO.class);
			LOGGER.info("{}", person);
			LOGGER.info("{}",person.getXmlJdbcConnection());
		}
	}

}
