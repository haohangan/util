package simple.rest.express.utils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TimeCounter {
	private static AtomicIntegerFieldUpdater<TimeCounter> UPDATER = AtomicIntegerFieldUpdater
			.newUpdater(TimeCounter.class, "time");
	public volatile int time = 0;
	AtomicBoolean bool = new AtomicBoolean(true);

	public long update() {
		return UPDATER.incrementAndGet(this);
	}

	public long get() {
		return time;
	}

	public void reset() {
		UPDATER.set(this, 0);
	}

	public void stop() {
		bool.set(false);
	}

	public void start() {
		UPDATER.set(this, 0);
		bool.set(true);
	}

	public boolean status() {
		return bool.get();
	}
}
