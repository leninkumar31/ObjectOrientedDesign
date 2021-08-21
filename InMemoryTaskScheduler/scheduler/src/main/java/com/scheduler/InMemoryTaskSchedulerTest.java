package com.scheduler;

import java.util.concurrent.TimeUnit;

import com.scheduler.exceptions.TaskRejectedException;
import com.scheduler.task.TaskScheduler;
import com.scheduler.task.TaskSchedulerFactory;

public class InMemoryTaskSchedulerTest {

	public static void main(String[] args) throws InterruptedException {
		TaskScheduler taskScheduler = TaskSchedulerFactory.newInMemoryTaskScheduler(10);
		taskScheduler.start();
		try {
			System.out.println(taskScheduler.scheduleAtFixedInterval(new TestTask(), 0, 100, TimeUnit.SECONDS));
			System.out.println(taskScheduler.schedule(new TestTask(), 10, TimeUnit.SECONDS));
			System.out.println(taskScheduler.schedule(new TestTask(), 102, TimeUnit.SECONDS));
		} catch (TaskRejectedException e) {
			System.out.println("Task is rejected:"+e.getMessage());
		}
		Thread.sleep(10000000);
	}
	
	static class TestTask implements Runnable{

		@Override
		public void run() {
			System.out.println("Task executed at:"+System.currentTimeMillis());
		}
		
	}
	
}
