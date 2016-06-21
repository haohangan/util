package org.app.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum ExecutorServicePool {
	INSTANCE;
	private ExecutorService pool = Executors.newCachedThreadPool();

	public void execute(Runnable run) {
		pool.execute(run);
	}

	public static void main(String[] args) {
		ExecutorServicePool.INSTANCE.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println(111111);
			}
		});
		try {
			Thread.sleep(70*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(ExecutorServicePool.INSTANCE);
		ExecutorServicePool.INSTANCE.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println(2222);
			}
		});
	}
}
