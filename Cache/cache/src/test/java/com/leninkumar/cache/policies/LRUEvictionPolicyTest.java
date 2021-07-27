package com.leninkumar.cache.policies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LRUEvictionPolicyTest {
	LRUEvictionPolicy<Integer> policy;

	@Before
	public void setUp() {
		policy = new LRUEvictionPolicy<>();
	}

	@Test
	public void testNoKeyEviction() {
		assertNull(policy.evictKey());
	}

	@Test
	public void testEvictionOrder() {
		policy.keyAccessed(1);
		policy.keyAccessed(2);
		policy.keyAccessed(3);
		policy.keyAccessed(4);
		assertEquals(1, (int)policy.evictKey());
		assertEquals(2, (int)policy.evictKey());
		assertEquals(3, (int)policy.evictKey());
		assertEquals(4, (int)policy.evictKey());
	}
}