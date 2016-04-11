package ti2.muliaccount;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
	// static StopWatch sw = new StopWatch();

	public static void main(String[] args) {
		// sw.start();
		Account account = new Account(1, 1000);
		String name = null;
		ReadWriteLock lock = new ReentrantReadWriteLock(false);
		ExecutorService pool = Executors.newFixedThreadPool(100);
		UserOperation uo = null;
		boolean isquery = false;
		Integer iocash = null;
		for (int i = 0; i < 1000; i++) {
			name = "第" + i + "用户";
			if (i % 2 == 0) {
				isquery = true;
				iocash = 1;
			} else {
				isquery = false;
				iocash = -2;
			}
			uo = new UserOperation(name, account, iocash, lock, isquery);
			pool.execute(uo);
		}
		pool.shutdown();
		// sw.stop();
		// System.out.println("耗時：" + sw.getTime() / 1000);
	}
}
