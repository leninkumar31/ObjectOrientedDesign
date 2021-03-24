package com.udemy.spring.basics.springdemo.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.udemy.spring.basics.springdemo.SpringDemoBasicApplication;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:/testContext.xml"})
class BinarySearchXmlTest {
	
	@Autowired
	BinarySearchImpl binarySearch;
	
	@Test
	void testBasicScenario() {
		assertEquals(3, binarySearch.binarySearch(new int[] {}, 5));
	}

}
