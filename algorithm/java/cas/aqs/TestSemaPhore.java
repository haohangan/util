package current.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestSemaPhore {

	public static void main(String[] args) {
		AtomicInteger i = new AtomicInteger(0);// 公共访问量

		// 线程池

		ExecutorService exec = Executors.newCachedThreadPool();

		// 只能5个线程同时访问
		final Semaphore semaphore = new Semaphore(5);

		for (int t = 0; t < 20; t++) {
			MyRunner mr = new MyRunner("td:" + t, semaphore, i);
			exec.submit(mr);
		}

		exec.shutdown();
	}

	static class MyRunner implements Runnable {
		String name;
		final Semaphore semaphore;
		AtomicInteger counter;

		public MyRunner(String name, Semaphore semaphore, AtomicInteger counter) {
			super();
			this.name = name;
			this.semaphore = semaphore;
			this.counter = counter;
		}

		@Override
		public void run() {
			try {
				semaphore.acquire();
				TimeUnit.SECONDS.sleep(5);
				System.out.println(name + "\t\t:\t\t" + counter.getAndIncrement());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphore.release();
			}
		}

	}
}
