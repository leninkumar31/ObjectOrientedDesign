package com.leninkumar.cache;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.leninkumar.cache.factories.CacheFactory;

@RunWith(JUnit4.class)
public class CacheTest {
	Cache<Integer, Integer> cache;

	@Before
	public void setUp() {
		cache = new CacheFactory<Integer, Integer>().defaultCache(3);
	}

	@Test
	public void testPutItem() {
		cache.put(1, 1);
		cache.put(2, 2);
		assertEquals(1, (int)cache.get(1));
		cache.put(3, 3);
		assertEquals(3, (int)cache.get(3));
		cache.put(4, 4);
		cache.get(2);
	}
}
