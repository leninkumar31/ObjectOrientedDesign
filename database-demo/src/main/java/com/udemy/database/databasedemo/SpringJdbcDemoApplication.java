package com.udemy.database.databasedemo;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udemy.database.databasedemo.entity.Person;
import com.udemy.database.databasedemo.jdbc.PersonJdbcDao;

// @SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonJdbcDao personDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Person> data = personDao.findAll();
		for(Person person : data) {
			logger.info("{}",person.toString());
		}
		
		logger.info("User: 10001 -> {}", personDao.findById(10001));
		
		logger.info("Deleting User: 10002. No of rows deleted {}", 
				personDao.deleteById(10002));
		
		logger.info("Inserting User: 10004. No of rows inserted {}", 
				personDao.insert(new Person(10004, "lenin", "Vizag", new Date())));
		
		logger.info("Update User: 10003. No of rows updated {}", 
				personDao.update(new Person(10003, "pavani", "Vizag", new Date())));
	}

}
