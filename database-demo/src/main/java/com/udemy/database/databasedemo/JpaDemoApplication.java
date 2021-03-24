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
import com.udemy.database.databasedemo.jpa.PersonJpaRepository;

// @SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonJpaRepository personDao;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Person> data = personDao.findAll();
		for(Person person : data) {
			logger.info("{}",person.toString());
		}
		
		logger.info("User: 10001 -> {}", personDao.findById(10001));
		
		personDao.delete(10002);
		
		logger.info("Inserting User: 10004. Inserted value: {}", 
				personDao.insert(new Person("lenin", "Vizag", new Date())));
		
		logger.info("Update User: 10003. Updated value: {}", 
				personDao.update(new Person(10003, "pavani", "Vizag", new Date())));
	}

}
