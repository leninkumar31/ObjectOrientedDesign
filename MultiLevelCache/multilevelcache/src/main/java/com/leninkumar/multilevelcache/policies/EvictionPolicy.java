package com.leninkumar.multilevelcache.policies;

public interface EvictionPolicy<K> {
	void keyAccessed(K key);

	K evictKey();
}
