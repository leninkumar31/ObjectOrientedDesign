package com.scheduler.task;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.scheduler.exceptions.TaskRejectedException;
import com.scheduler.models.ScheduledTask;

import lombok.Getter;

@Getter
public class InMemoryTaskScheduler implements TaskScheduler {

	private volatile long taskId;

	private ExecutorService threadPoolExecutor;

	private PriorityQueue<ScheduledTask> delayQueue;
	
	private Thread scheduler;

	public InMemoryTaskScheduler(int workerThreads) {
		this.threadPoolExecutor = Executors.newFixedThreadPool(workerThreads);
		this.taskId = 0;
		this.delayQueue = new PriorityQueue<>();
		scheduler = new Thread(() -> {
			run();
		});
		scheduler.setDaemon(true);
	}

	@Override
	public synchronized long schedule(Runnable task, long delay, TimeUnit unit) throws TaskRejectedException {
		this.taskId++;
		ScheduledTask scheduledTask = new ScheduledTask(this.taskId, task, delay, unit);
		System.out.println(scheduledTask.getId());
		delayQueue.add(scheduledTask);
		notify();
		return scheduledTask.getId();
	}

	@Override
	public synchronized long scheduleAtFixedInterval(Runnable task, long initDelay, long interval, TimeUnit unit)
			throws TaskRejectedException {
		this.taskId++;
		ScheduledTask scheduledTask = new ScheduledTask(this.taskId, task, initDelay, interval, unit);
		System.out.println(scheduledTask.getId());
		delayQueue.add(scheduledTask);
		notify();
		return scheduledTask.getId();
	}
	
	@Override
	public void start() {
		this.scheduler.start();
	}

	private void run() {
		for (;;) {
			synchronized (this) {
				while (delayQueue.isEmpty()) {
					try {
						wait();
					} catch (InterruptedException e) {
						System.out.println("Interrupted");
					}
				}
				while (!delayQueue.isEmpty()) {
					ScheduledTask task = delayQueue.peek();
					long timeDiff = task.getNextExecutionTimeInMs() - System.currentTimeMillis();
					if (timeDiff <= 0) {
						System.out.println("task :" + task.getId() + "is supposed to execute at:"
								+ task.getNextExecutionTimeInMs());
						threadPoolExecutor.execute(task.getTask());
						ScheduledTask removedTask = delayQueue.poll();
						if (removedTask.isPeriodic()) {
							removedTask.setNextExecutionTimeInMs(removedTask.getNextExecutionTimeInMs()
									+ task.getUnit().toMillis(removedTask.getInterval()));
							delayQueue.add(removedTask);
						}
						notifyAll();
					} else {
						try {
							wait(timeDiff);
						} catch (InterruptedException e) {
							System.out.println("interrupted");
						}
					}
				}
			}
		}
	}

}
