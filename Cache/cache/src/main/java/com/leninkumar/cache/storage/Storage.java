/**
 * 
 */
package com.leninkumar.cache.storage;

import com.leninkumar.cache.exceptions.KeyNotFoundException;
import com.leninkumar.cache.exceptions.StorageFullException;

/**
 * @author lpothabattula
 *
 */
public interface Storage<K, V> {
	void add(K key, V value) throws StorageFullException;

	V get(K key) throws KeyNotFoundException;

	void remove(K key) throws KeyNotFoundException;
}
