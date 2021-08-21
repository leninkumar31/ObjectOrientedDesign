package com.scheduler.task;

public class TaskSchedulerFactory {
	public static TaskScheduler newInMemoryTaskScheduler(int maxThreads) {
		return new InMemoryTaskScheduler(maxThreads);
	}
}
