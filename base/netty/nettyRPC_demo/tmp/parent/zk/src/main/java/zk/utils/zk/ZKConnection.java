package zk.utils.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import log.CommonLogger;

public class ZKConnection {
	// Local Zookeeper object to access ZooKeeper ensemble
	private ZooKeeper zoo;
	final CountDownLatch connectionLatch = new CountDownLatch(1);

	public ZKConnection() {
	}

	/**
	 * Initialize the Zookeeper connection
	 * 
	 * @param host
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public ZooKeeper connect(String host) throws IOException, InterruptedException {

		zoo = new ZooKeeper(host, 2000, new Watcher() {

			public void process(WatchedEvent we) {
				CommonLogger.info("process countDown:" + we.getState());
				if (we.getState() == KeeperState.SyncConnected) {
					CommonLogger.info("SyncConnected countDown");
					connectionLatch.countDown();
				}
			}
		});
		CommonLogger.info("before wait");
		connectionLatch.await();
		CommonLogger.info("after wait");
		return zoo;
	}

	// Method to disconnect from zookeeper server
	public void close() throws InterruptedException {
		CommonLogger.info("close connection");
		zoo.close();
	}
}
