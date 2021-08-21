package com.scheduler.models;

import java.util.concurrent.TimeUnit;

import com.scheduler.exceptions.TaskRejectedException;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class ScheduledTask implements Comparable<ScheduledTask> {
	private long id;
	private Runnable task;
	private long initDelay;
	private long interval;
	private boolean isPeriodic;
	@Setter
	private long nextExecutionTimeInMs;
	private TimeUnit unit;

	public ScheduledTask(long id, Runnable task, long initDelay, TimeUnit unit) throws TaskRejectedException {
		if (task == null || initDelay < 0) {
			throw new TaskRejectedException("Either task is null or delay is less than zero");
		}
		this.id = id;
		this.task = task;
		this.initDelay = initDelay;
		this.unit = unit;
		this.nextExecutionTimeInMs = System.currentTimeMillis() + unit.toMillis(initDelay);
	}

	public ScheduledTask(long id, @NonNull Runnable task, long initDelay, long interval, TimeUnit unit)
			throws TaskRejectedException {
		this(id, task, initDelay, unit);
		if (interval < 0) {
			throw new TaskRejectedException("Interval is less than zero");
		}
		this.isPeriodic = true;
		this.interval = interval;
	}

	@Override
	public int compareTo(ScheduledTask l) {
		return Long.compare(this.nextExecutionTimeInMs, l.getNextExecutionTimeInMs());
	}
}
