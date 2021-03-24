package com.udemy.spring.basics.springdemo.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BinarySearchImpl {
	
	private Logger logger = LoggerFactory.getLogger(BinarySearchImpl.class);
	@Autowired
	@Qualifier("bubble")
	private SortAlgorithm sortAlgo;

	public int binarySearch(int[] numbers, int numberToBeSearched) {
		this.sortAlgo.sort(numbers);
		System.out.println(sortAlgo);
		return 3;
	}
	
//	@PostConstruct
//	public void postConstrut() {
//		logger.info("Entered post construct");
//	}
//	
//	@PreDestroy
//	public void preDestroy() {
//		logger.info("Entered pre destroy");
//	}
}
