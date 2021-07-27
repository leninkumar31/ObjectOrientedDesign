package com.leninkumar.multilevelcache.provider;

import com.leninkumar.multilevelcache.exceptions.KeyNotFoundException;
import com.leninkumar.multilevelcache.exceptions.StorageFullException;
import com.leninkumar.multilevelcache.policies.EvictionPolicy;
import com.leninkumar.multilevelcache.storage.Storage;

public class CacheProvider<K, V> {
	private final Storage<K, V> storage;
	private final EvictionPolicy<K> evictionPolicy;

	public CacheProvider(Storage<K, V> storage, EvictionPolicy<K> evictionPolicy) {
		this.storage = storage;
		this.evictionPolicy = evictionPolicy;
	}

	public V get(K key) {
		try {
			V val = this.storage.get(key);
			this.evictionPolicy.keyAccessed(key);
			return val;
		} catch (KeyNotFoundException e) {
			System.out.println("Key doesn't exist");
			return null;
		}
	}

	public void put(K key, V value) {
		try {
			this.storage.put(key, value);
			this.evictionPolicy.keyAccessed(key);
		} catch (StorageFullException e) {
			K evictedValue = this.evictionPolicy.evictKey();
			if (evictedValue == null) {
				throw new RuntimeException("Something is wrong!. Storage is full but evicted value is null");
			}
			try {
				this.storage.remove(key);
			} catch (KeyNotFoundException e1) {
				System.out.println("Evicted key doesn't exist");
			}
			put(key, value);
		}
	}

	public Storage<K, V> getStorage() {
		return this.storage;
	}
}
