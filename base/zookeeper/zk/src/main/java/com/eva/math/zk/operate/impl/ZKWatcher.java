package com.eva.math.zk.operate.impl;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ZKWatcher implements Watcher{
	
	CountDownLatch latch;
	
	public ZKWatcher() {
		super();
		latch = new CountDownLatch(1);
	}

	@Override
	public void process(WatchedEvent event) {
		latch.countDown();
	}
	
	public void await() throws InterruptedException{
		latch.await();
	}

}
