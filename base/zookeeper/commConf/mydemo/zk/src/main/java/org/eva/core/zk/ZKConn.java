package org.eva.core.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

/**
 * zookeeper 建立连接的方法
 * 
 * @author 97617
 *
 */
class ZKConn {
	private static final int SESSION_TIME_OUT = 3 * 1000;

	public static ZooKeeper getConn(String connectString) throws IOException, InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		ZooKeeper zk = new ZooKeeper(connectString, SESSION_TIME_OUT, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if (event.getState() == KeeperState.SyncConnected) {
					latch.countDown();
				}
			}
		});
		latch.await(SESSION_TIME_OUT * 3, TimeUnit.MILLISECONDS);
		if (zk.getState() != States.CONNECTED) {
			throw new IOException("未连接成功");
		}
		return zk;
	}
	
	public static void close(ZooKeeper zk) throws InterruptedException{
		if(zk!=null && zk.getState()==States.CONNECTED){
			zk.close();
			System.out.println("已关闭");
		}else{
			System.out.println("连接无需关闭:"+zk);
		}
	}
}
