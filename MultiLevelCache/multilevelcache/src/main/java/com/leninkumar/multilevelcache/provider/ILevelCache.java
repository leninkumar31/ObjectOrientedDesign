package com.leninkumar.multilevelcache.provider;

import java.util.List;

import com.leninkumar.multilevelcache.models.ReadResponse;
import com.leninkumar.multilevelcache.models.WriteResponse;

public interface ILevelCache<K, V> {
	ReadResponse<V> get(K key);

	WriteResponse put(K key, V value);

	List<Double> getUsages();
}
