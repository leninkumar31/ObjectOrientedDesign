package com.leninkumar.cache.policies;

public interface EvictionPolicy<K> {
	K evictKey();

	void keyAccessed(K key);
}
