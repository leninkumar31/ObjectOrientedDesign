package com.leninkumar.cache;

import com.leninkumar.cache.exceptions.KeyNotFoundException;
import com.leninkumar.cache.exceptions.StorageFullException;
import com.leninkumar.cache.policies.EvictionPolicy;
import com.leninkumar.cache.storage.Storage;

public class Cache<K, V> {
	private final Storage<K, V> storage;
	private final EvictionPolicy<K> policy;

	public Cache(Storage<K, V> storage, EvictionPolicy<K> policy) {
		this.storage = storage;
		this.policy = policy;
	}

	public void put(K key, V value) {
		try {
			this.storage.add(key, value);
			this.policy.keyAccessed(key);
		} catch (StorageFullException e) {
			System.out.println("Got storage full. Will try to evict now");
			K evictKey = this.policy.evictKey();
			if (evictKey == null) {
				throw new RuntimeException("Something is wrong. Storage is full. But couldn't evict a value");
			}
			try {
				this.storage.remove(evictKey);
			} catch (KeyNotFoundException e1) {
				System.out.println("Evicted key:" + evictKey + " doesn't exist");
			}
			put(key, value);
		}
	}

	public V get(K key) {
		try {
			V val = this.storage.get(key);
			this.policy.keyAccessed(key);
			return val;
		} catch (KeyNotFoundException e) {
			System.out.println("Key is not found: " + key);
			return null;
		}
	}
}
