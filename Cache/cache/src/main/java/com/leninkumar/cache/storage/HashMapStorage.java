package com.leninkumar.cache.storage;

import java.util.HashMap;
import java.util.Map;

import com.leninkumar.cache.exceptions.KeyNotFoundException;
import com.leninkumar.cache.exceptions.StorageFullException;

public class HashMapStorage<K, V> implements Storage<K, V> {

	private final Map<K, V> cache;
	private final int size;

	public HashMapStorage(int size) {
		this.cache = new HashMap<>();
		this.size = size;
	}

	@Override
	public void add(K key, V value) throws StorageFullException {
		if (this.cache.size() == this.size) {
			throw new StorageFullException("Storage is full");
		}
		this.cache.put(key, value);
	}

	@Override
	public V get(K key) throws KeyNotFoundException {
		if (!this.cache.containsKey(key)) {
			throw new KeyNotFoundException("Given key:" + key + " is not found");
		}
		return this.cache.get(key);
	}

	@Override
	public void remove(K key) throws KeyNotFoundException {
		if (!this.cache.containsKey(key)) {
			throw new KeyNotFoundException("Given key:" + key + " is not found");
		}
		this.cache.remove(key);
	}

	public int getSize() {
		return size;
	}
}
