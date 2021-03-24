package com.udemy.mockito.mockitodemo;

public class SomeBusinessImpl {
	private DataService dataService;
	
	public SomeBusinessImpl(DataService service) {
		this.dataService = service;
	}
	
	public int findGreatestFromAllData() {
		int[] data = this.dataService.retrieveAllData();
		int maxVal = Integer.MIN_VALUE;
		for(int val : data) {
			maxVal = Math.max(val, maxVal);
		}
		return maxVal;
	}
}
