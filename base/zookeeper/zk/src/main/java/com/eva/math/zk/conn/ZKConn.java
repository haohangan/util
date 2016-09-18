package com.eva.math.zk.conn;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZKConn {
	private static String ADDR = "127.0.0.1";
	private static int PORT = 2181;
	private static final int SESSION_TIME = 3000;

	private ZooKeeper zoo;
	private final CountDownLatch latch = new CountDownLatch(1);

	public ZKConn() throws IOException, Exception {
		zoo = conn(ADDR + ":" + PORT);
	}

	private ZooKeeper conn(String host) throws IOException, InterruptedException {
		ZooKeeper tzk = null;
		tzk = new ZooKeeper(host, SESSION_TIME, new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				if (event.getState() == KeeperState.SyncConnected) {
					Logger.getGlobal().info("ZooKeeper conn success");
					latch.countDown();
				}
			}
		});
		latch.await();
		return tzk;
	}

	public void close() {
		if (zoo != null) {
			try {
				zoo.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public ZooKeeper getZK() {
		return zoo;
	}
}
