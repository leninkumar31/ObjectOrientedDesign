package com.udemy.spring.basics.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.udemy.spring.basics.springdemo.basic.BinarySearchImpl;

@Configuration
@ComponentScan
public class SpringDemoBasicApplication {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				SpringDemoBasicApplication.class)) {
			BinarySearchImpl binarySearch = context.getBean(BinarySearchImpl.class);
			System.out.println(binarySearch);
			BinarySearchImpl binarySearch1 = context.getBean(BinarySearchImpl.class);
			System.out.println(binarySearch1);
			int result = binarySearch.binarySearch(new int[] { 12, 3, 4 }, 3);
			System.out.println(result);
		}
	}

}
