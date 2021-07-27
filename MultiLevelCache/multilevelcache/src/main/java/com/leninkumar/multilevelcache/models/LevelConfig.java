package com.leninkumar.multilevelcache.models;

public class LevelConfig {
	private final double readTime;
	private final double writeTime;

	public LevelConfig(double readTime, double writeTime) {
		this.readTime = readTime;
		this.writeTime = writeTime;
	}

	public double getReadTime() {
		return this.readTime;
	}

	public double getWriteTime() {
		return this.writeTime;
	}
}
