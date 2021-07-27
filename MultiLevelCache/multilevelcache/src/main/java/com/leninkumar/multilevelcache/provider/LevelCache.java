package com.leninkumar.multilevelcache.provider;

import java.util.ArrayList;
import java.util.List;

import com.leninkumar.multilevelcache.models.LevelConfig;
import com.leninkumar.multilevelcache.models.ReadResponse;
import com.leninkumar.multilevelcache.models.WriteResponse;

public class LevelCache<K, V> implements ILevelCache<K, V> {

	private final LevelConfig config;

	private ILevelCache<K, V> next;

	private CacheProvider<K, V> cache;

	public LevelCache(LevelConfig config, CacheProvider<K, V> cache, ILevelCache<K, V> next) {
		this.config = config;
		this.cache = cache;
		this.next = next;
	}

	@Override
	public ReadResponse<V> get(K key) {
		double timeTaken = this.config.getReadTime();
		V val = this.cache.get(key);
		if (val != null) {
			return new ReadResponse<>(timeTaken, val);
		}
		if (this.next != null) {
			ReadResponse<V> response = this.next.get(key);
			val = response.getValue();
			if (val != null) {
				this.cache.put(key, val);
				timeTaken += this.config.getWriteTime();
			}
			timeTaken += response.getReadTime();
		}
		return new ReadResponse<>(timeTaken, val);
	}

	@Override
	public WriteResponse put(K key, V value) {
		V val = this.cache.get(key);
		double timeTaken = this.config.getReadTime();
		if (val == null || !val.equals(value)) {
			this.cache.put(key, value);
			timeTaken += this.config.getWriteTime();
		}
		if (this.next != null) {
			WriteResponse response = this.next.put(key, value);
			timeTaken += response.getWriteTime();
		}
		return new WriteResponse(timeTaken);
	}

	@Override
	public List<Double> getUsages() {
		List<Double> usages;
		if (this.next == null) {
			usages = new ArrayList<>();
		} else {
			usages = this.next.getUsages();
		}
		usages.add(0, this.cache.getStorage().getCurrentUsage());
		return usages;
	}

}
