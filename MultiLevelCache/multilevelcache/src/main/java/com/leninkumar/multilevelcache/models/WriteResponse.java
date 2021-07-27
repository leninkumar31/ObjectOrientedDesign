package com.leninkumar.multilevelcache.models;

public class WriteResponse {
	double writeTime;

	public WriteResponse() {

	}

	public WriteResponse(double writeTime) {
		this.writeTime = writeTime;
	}

	public double getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(double writeTime) {
		this.writeTime = writeTime;
	}

}
