package simple.rest.express.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public enum CommonSchedule {
	INSTANCE;

	private ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();

	public void add(Runnable run, long delay, long peroid) {
		pool.scheduleWithFixedDelay(run, delay, peroid, TimeUnit.SECONDS);
	}
}
