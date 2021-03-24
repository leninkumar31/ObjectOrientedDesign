package com.udemy.spring.basics.springdemo.cdi;

import javax.inject.Named;

@Named
public class SomeCdiDao {
	public int[] getData() {
		return new int[] {12, 14, 13};
	}
}
