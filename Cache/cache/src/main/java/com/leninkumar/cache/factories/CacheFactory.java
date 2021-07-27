package com.leninkumar.cache.factories;

import com.leninkumar.cache.Cache;
import com.leninkumar.cache.policies.LRUEvictionPolicy;
import com.leninkumar.cache.storage.HashMapStorage;

public class CacheFactory<K, V> {
	public Cache<K, V> defaultCache(final int capacity) {
		return new Cache<K, V>(new HashMapStorage<K, V>(capacity), new LRUEvictionPolicy<K>());
	}
}
