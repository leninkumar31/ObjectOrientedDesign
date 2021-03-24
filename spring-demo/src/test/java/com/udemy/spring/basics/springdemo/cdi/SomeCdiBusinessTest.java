package com.udemy.spring.basics.springdemo.cdi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SomeCdiBusinessTest {
	
	@Mock
	SomeCdiDao daoMock;
	
	@InjectMocks
	SomeCdiBusiness business;
	
	@Test
	void testBasicScenario1() {
		Mockito.when(daoMock.getData()).thenReturn(new int[] {1,2});
		assertEquals(2, business.findGreatest());
	}
	
	@Test
	void testBasicScenario2() {
		Mockito.when(daoMock.getData()).thenReturn(new int[] {});
		assertEquals(Integer.MIN_VALUE, business.findGreatest());
	}

}
