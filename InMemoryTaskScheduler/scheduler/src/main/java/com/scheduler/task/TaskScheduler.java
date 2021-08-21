package com.scheduler.task;

import java.util.concurrent.TimeUnit;

import com.scheduler.exceptions.TaskRejectedException;

public interface TaskScheduler {
	public long schedule(Runnable task, long delay, TimeUnit unit) throws TaskRejectedException;

	public long scheduleAtFixedInterval(Runnable task, long initDelay, long interval, TimeUnit unit)
			throws TaskRejectedException;

	public void start();
}
