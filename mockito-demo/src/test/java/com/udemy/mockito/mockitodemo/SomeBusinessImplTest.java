package com.udemy.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SomeBusinessImplTest {

	@Test
	void testFindGreatestFromAllData() {
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(new DataServiceImpl());
		int val = businessImpl.findGreatestFromAllData();
		assertEquals(14, val);
	}

}

class DataServiceImpl implements DataService{
	@Override
	public int[] retrieveAllData() {
		return new int[] {12, 13, 14};
	}
	
}
