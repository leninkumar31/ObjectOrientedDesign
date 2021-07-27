package com.leninkumar.multilevelcache.models;

public class ReadResponse<V> {
	double readTime;
	V value;

	public ReadResponse() {

	}

	public ReadResponse(double readTime, V value) {
		this.readTime = readTime;
		this.value = value;
	}

	public double getReadTime() {
		return readTime;
	}

	public void setReadTime(double readTime) {
		this.readTime = readTime;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

}
