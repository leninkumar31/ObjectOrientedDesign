package com.leninkumar.multilevelcache.storage;

import com.leninkumar.multilevelcache.exceptions.KeyNotFoundException;
import com.leninkumar.multilevelcache.exceptions.StorageFullException;

public interface Storage<K, V> {
	V get(K key) throws KeyNotFoundException;

	void put(K key, V value) throws StorageFullException;

	void remove(K key) throws KeyNotFoundException;

	double getCurrentUsage();
}
