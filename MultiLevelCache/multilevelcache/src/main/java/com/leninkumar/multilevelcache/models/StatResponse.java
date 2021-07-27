package com.leninkumar.multilevelcache.models;

import java.util.List;

public class StatResponse {
	double avgReadTime;
	double avgWriteTime;
	List<Double> cacheOccupancies;

	public StatResponse() {

	}

	public StatResponse(double avgReadTime, double avgWriteTime, List<Double> cacheOccupancies) {
		this.avgReadTime = avgReadTime;
		this.avgWriteTime = avgWriteTime;
		this.cacheOccupancies = cacheOccupancies;
	}

	public double getAvgReadTime() {
		return avgReadTime;
	}

	public void setAvgReadTime(double avgReadTime) {
		this.avgReadTime = avgReadTime;
	}

	public double getAvgWriteTime() {
		return avgWriteTime;
	}

	public void setAvgWriteTime(double avgWriteTime) {
		this.avgWriteTime = avgWriteTime;
	}

}
