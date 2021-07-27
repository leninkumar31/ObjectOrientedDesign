package com.leninkumar.multilevelcache.storage;

import java.util.HashMap;
import java.util.Map;

import com.leninkumar.multilevelcache.exceptions.KeyNotFoundException;
import com.leninkumar.multilevelcache.exceptions.StorageFullException;

public class HashMapBasedStorage<K, V> implements Storage<K, V> {

	private final int capacity;
	private final Map<K, V> cache;

	public HashMapBasedStorage(int capacity) {
		this.capacity = capacity;
		this.cache = new HashMap<>();
	}

	@Override
	public V get(K key) throws KeyNotFoundException {
		if (!this.cache.containsKey(key)) {
			throw new KeyNotFoundException("Key doesn't exists");
		}
		return this.cache.get(key);
	}

	@Override
	public void put(K key, V value) throws StorageFullException {
		if (this.isFull()) {
			throw new StorageFullException("Storage is full");
		} else {
			this.cache.put(key, value);
		}
	}

	@Override
	public void remove(K key) throws KeyNotFoundException {
		if (!this.cache.containsKey(key)) {
			throw new KeyNotFoundException("Key doesn't exists");
		}
		this.cache.remove(key);
	}

	private boolean isFull() {
		return this.cache.size() == this.capacity;
	}

	@Override
	public double getCurrentUsage() {
		return (1.0 * (this.cache.size())) / (this.capacity);
	}

}
