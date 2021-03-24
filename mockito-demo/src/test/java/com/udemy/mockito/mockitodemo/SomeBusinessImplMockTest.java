package com.udemy.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SomeBusinessImplMockTest {

	@Test
	void testFindGreatestFromAllData() {
		DataService dataServiceMock = Mockito.mock(DataService.class);
		Mockito.when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {12, 13, 14});
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int val = businessImpl.findGreatestFromAllData();
		assertEquals(14, val);
	}
	
	@Test
	void testFindGreatestFromAllDataForOneValue() {
		DataService dataServiceMock = Mockito.mock(DataService.class);
		Mockito.when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {14});
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int val = businessImpl.findGreatestFromAllData();
		assertEquals(14, val);
	}

}

