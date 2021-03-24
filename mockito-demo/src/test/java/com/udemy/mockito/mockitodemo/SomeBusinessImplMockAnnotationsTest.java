package com.udemy.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockAnnotationsTest {
	
	@Mock
	DataService dataServiceMock;
	
	@InjectMocks
	SomeBusinessImpl businessImpl;

	@Test
	void testFindGreatestFromAllData() {
		Mockito.when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {12, 13, 14});
		assertEquals(14, businessImpl.findGreatestFromAllData());
	}
	
	@Test
	void testFindGreatestFromAllDataForOneValue() {
		Mockito.when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {14});
		assertEquals(14, businessImpl.findGreatestFromAllData());
	}
	
	@Test
	void testFindGreatestFromAllDataForNoValue() {
		Mockito.when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(Integer.MIN_VALUE, businessImpl.findGreatestFromAllData());
	}

}