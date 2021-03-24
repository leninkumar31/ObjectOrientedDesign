package com.udemy.spring.aop.springaop.dao;

import org.springframework.stereotype.Repository;

import com.udemy.spring.aop.springaop.aspect.TrackTime;

@Repository
public class Dao1 {
	@TrackTime
	public String getSomething() {
		return "This is Dao1";
	}
}
