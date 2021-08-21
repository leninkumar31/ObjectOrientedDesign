package com.scheduler.task;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.scheduler.exceptions.TaskRejectedException;
import com.scheduler.models.ScheduledTask;

import lombok.Getter;

@Getter
public class InMemoryTaskScheduler implements TaskScheduler {

	private volatile AtomicLong taskId;

	private ExecutorService threadPoolExecutor;

	private PriorityQueue<ScheduledTask> delayQueue;

	private Thread scheduler;

	private ReentrantLock lock;

	private Condition newTaskArrived;

	public InMemoryTaskScheduler(int workerThreads) {
		this.threadPoolExecutor = Executors.newFixedThreadPool(workerThreads);
		this.taskId = new AtomicLong();
		this.lock = new ReentrantLock();
		this.newTaskArrived = this.lock.newCondition();
		this.delayQueue = new PriorityQueue<>();
		scheduler = new Thread(() -> {
			run();
		});
		scheduler.setDaemon(true);
	}

	@Override
	public long schedule(Runnable task, long delay, TimeUnit unit) throws TaskRejectedException {
		this.taskId.incrementAndGet();
		ScheduledTask scheduledTask = new ScheduledTask(this.taskId.get(), task, delay, unit);
		lock.lock();
		delayQueue.add(scheduledTask);
		this.newTaskArrived.signal();
		lock.unlock();
		return scheduledTask.getId();
	}

	@Override
	public long scheduleAtFixedInterval(Runnable task, long initDelay, long interval, TimeUnit unit)
			throws TaskRejectedException {
		this.taskId.incrementAndGet();
		ScheduledTask scheduledTask = new ScheduledTask(this.taskId.get(), task, initDelay, interval, unit);
		lock.lock();
		delayQueue.add(scheduledTask);
		this.newTaskArrived.signal();
		lock.unlock();
		return scheduledTask.getId();
	}

	@Override
	public void start() {
		this.scheduler.start();
	}

	private void run() {
		for (;;) {
			lock.lock();
			while (delayQueue.isEmpty()) {
				try {
					this.newTaskArrived.await();
				} catch (InterruptedException e) {
					System.out.println("Interrupted");
				}
			}
			while (!delayQueue.isEmpty()) {
				ScheduledTask task = delayQueue.peek();
				long timeDiff = task.getNextExecutionTimeInMs() - System.currentTimeMillis();
				if (timeDiff <= 0) {
					System.out.println(
							"task :" + task.getId() + "is supposed to execute at:" + task.getNextExecutionTimeInMs());
					threadPoolExecutor.execute(task.getTask());
					ScheduledTask removedTask = delayQueue.poll();
					if (removedTask.isPeriodic()) {
						removedTask.setNextExecutionTimeInMs(removedTask.getNextExecutionTimeInMs()
								+ task.getUnit().toMillis(removedTask.getInterval()));
						delayQueue.add(removedTask);
					}
				} else {
					try {
						this.newTaskArrived.await(timeDiff, TimeUnit.MILLISECONDS);
					} catch (InterruptedException e) {
						System.out.println("interrupted");
					}
				}
			}
			lock.unlock();
		}
	}

}
