package com.leninkumar.multilevelcache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.leninkumar.multilevelcache.models.LevelConfig;
import com.leninkumar.multilevelcache.models.ReadResponse;
import com.leninkumar.multilevelcache.models.WriteResponse;
import com.leninkumar.multilevelcache.policies.LRUEvictionPolicy;
import com.leninkumar.multilevelcache.provider.CacheProvider;
import com.leninkumar.multilevelcache.provider.ILevelCache;
import com.leninkumar.multilevelcache.provider.LevelCache;
import com.leninkumar.multilevelcache.storage.HashMapBasedStorage;

@RunWith(JUnit4.class)
public class CacheServiceTest {

	@Test
	public void testCacheService() {
		CacheProvider<Integer, Integer> cacheProvider1 = createCacheProvider(10);
		CacheProvider<Integer, Integer> cacheProvider2 = createCacheProvider(20);

		LevelConfig config1 = new LevelConfig(1, 3);
		LevelConfig config2 = new LevelConfig(2, 4);

		ILevelCache<Integer, Integer> level2 = new LevelCache<Integer, Integer>(config2, cacheProvider2, null);
		ILevelCache<Integer, Integer> level1 = new LevelCache<Integer, Integer>(config1, cacheProvider1, level2);

		CacheService<Integer, Integer> cacheService = new CacheService<Integer, Integer>(level1, 5);

		WriteResponse w1 = cacheService.put(1, 1);
		WriteResponse w2 = cacheService.put(2, 2);
		assertEquals(10.0, w1.getWriteTime(), 1e-5);
		assertEquals(10.0, w2.getWriteTime(), 1e-5);

		ReadResponse<Integer> r1 = cacheService.get(1);
		assertEquals(1.0, r1.getReadTime(), 1e-5);
		assertEquals(1, (int) r1.getValue());
		
		ReadResponse<Integer> r2 = cacheService.get(2);
		assertEquals(1.0, r2.getReadTime(), 1e-5);
		assertEquals(2, (int) r2.getValue());
		
		cacheProvider1.put(1, null);
		
		ReadResponse<Integer> r3 = cacheService.get(1);
		assertEquals(6.0, r3.getReadTime(), 1e-5);
		assertEquals(1, (int) r3.getValue());
		
		ReadResponse<Integer> r4 = cacheService.get(1);
		assertEquals(1.0, r4.getReadTime(), 1e-5);
		assertEquals(1, (int) r4.getValue());
		
		WriteResponse w3 = cacheService.put(1, 1);
		assertEquals(3.0, w3.getWriteTime(), 1e-5);
		
	}

	private CacheProvider<Integer, Integer> createCacheProvider(int capacity) {
		return new CacheProvider<Integer, Integer>(new HashMapBasedStorage<Integer, Integer>(capacity),
				new LRUEvictionPolicy<Integer>());
	}
}
