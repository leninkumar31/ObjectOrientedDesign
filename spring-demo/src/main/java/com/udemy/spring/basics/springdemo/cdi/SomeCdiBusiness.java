package com.udemy.spring.basics.springdemo.cdi;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SomeCdiBusiness {
	
	@Inject
	private SomeCdiDao someCdiDao;

	public SomeCdiDao getSomeCdiDao() {
		return someCdiDao;
	}

	public void setSomeCdiDao(SomeCdiDao someCdiDao) {
		this.someCdiDao = someCdiDao;
	}
	
	public int findGreatest() {
		int[] data = this.someCdiDao.getData();
		int maxVal = Integer.MIN_VALUE;
		for(int val: data) {
			maxVal = Math.max(val, maxVal);
		}
		return maxVal;
	}
}
