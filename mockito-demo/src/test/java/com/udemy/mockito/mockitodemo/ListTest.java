package com.udemy.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListTest {
	
	@Test
	void testSingleRetrurn() {
		List listMock = Mockito.mock(List.class);
		Mockito.when(listMock.size()).thenReturn(10);
		assertEquals(10, listMock.size());
		assertEquals(10, listMock.size());
	}

	@Test
	void testMultipleReturns() {
		List listMock = Mockito.mock(List.class);
		Mockito.when(listMock.size()).thenReturn(10).thenReturn(20);
		assertEquals(10, listMock.size());
		assertEquals(20, listMock.size());
	}
	
	@Test
	void testGet() {
		List listMock = Mockito.mock(List.class);
		Mockito.when(listMock.get(Mockito.anyInt())).thenReturn("Something");
		assertEquals("Something", listMock.get(0));
		assertEquals("Something", listMock.get(1));
	}

}
