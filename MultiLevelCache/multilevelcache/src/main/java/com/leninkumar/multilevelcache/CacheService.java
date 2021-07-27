package com.leninkumar.multilevelcache;

import java.util.ArrayList;
import java.util.List;

import com.leninkumar.multilevelcache.models.ReadResponse;
import com.leninkumar.multilevelcache.models.WriteResponse;
import com.leninkumar.multilevelcache.provider.ILevelCache;

public class CacheService<K, V> {
	private final ILevelCache<K, V> multiLevelCache;
	private final List<Double> lastReads;
	private final List<Double> lastWrites;
	private final int lastCnt;

	public CacheService(ILevelCache<K, V> multiLevelCache, int lastCnt) {
		this.multiLevelCache = multiLevelCache;
		this.lastCnt = lastCnt;
		this.lastReads = new ArrayList<>();
		this.lastWrites = new ArrayList<>();
	}

	public ReadResponse<V> get(K key) {
		ReadResponse<V> response = this.multiLevelCache.get(key);
		if (this.lastReads.size() == lastCnt) {
			this.lastReads.remove(0);
		}
		this.lastReads.add(response.getReadTime());
		return response;
	}

	public WriteResponse put(K key, V value) {
		WriteResponse response = this.multiLevelCache.put(key, value);
		if (this.lastWrites.size() == lastCnt) {
			this.lastWrites.remove(0);
		}
		this.lastWrites.add(response.getWriteTime());
		return response;
	}

	public double avgLastReads() {
		double sum = 0.0;
		for (int i = 0; i < this.lastReads.size(); i++) {
			sum += this.lastReads.get(i);
		}
		return sum / this.lastReads.size();
	}

	public double avgLastWrites() {
		double sum = 0.0;
		for (int i = 0; i < this.lastWrites.size(); i++) {
			sum += this.lastWrites.get(i);
		}
		return sum / this.lastWrites.size();
	}
}
