package org.eva.core.zk;

import java.nio.charset.StandardCharsets;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.eva.core.cache.CommCache;

public class ZKManager {
	private ZooKeeper zookeeper;

	public ZKManager(ZooKeeper zookeeper) {
		super();
		this.zookeeper = zookeeper;
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			ZKConn.close(zookeeper);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("关闭zk成功");
	}

	/**
	 * 创建或更新节点
	 * 
	 * @param path
	 * @param data
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void createEphemeral(String path, byte[] data) throws KeeperException, InterruptedException {
		Stat stat = getStat(path);
		if (stat == null) {
			zookeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		} else {
			zookeeper.setData(path, data, stat.getVersion());
		}
	}

	/**
	 * 获取状态
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public Stat getStat(String path) throws KeeperException, InterruptedException {
		return zookeeper.exists(path, false);
	}

	/**
	 * 获取某路径下的数据
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public byte[] getData(String path) throws KeeperException, InterruptedException {
		Stat stat = getStat(path);
		if (stat == null) {
			return null;
		}
		return zookeeper.getData(path, false, stat);
	}
	
	// public void watchPath() throws KeeperException, InterruptedException {
	// Stat stat = getStat(APP_PATH);
	// if (stat == null) {
	// zookeeper.create(APP_PATH, "app parent".getBytes(StandardCharsets.UTF_8),
	// ZooDefs.Ids.OPEN_ACL_UNSAFE,
	// CreateMode.PERSISTENT);
	// }
	// zookeeper.exists(APP_PATH, new Watcher() {
	// @Override
	// public void process(WatchedEvent event) {
	// System.out.println("=====================start:");
	// System.out.println(event.getState());
	// System.out.println(event.getType());
	// System.out.println(event.getPath());
	// System.out.println("=====================end");
	// }
	// });
	// }

	public void watchPathLoop(String APP_PATH) throws KeeperException, InterruptedException {
		Stat stat = getStat(APP_PATH);
		if (stat == null) {
			zookeeper.create(APP_PATH, "app parent".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
		}
		int i = 0;
		zookeeper.exists(APP_PATH, new NodeWatcher(i + 1,APP_PATH));
	}

	class NodeWatcher implements Watcher {
		int time;
		String path;

		public NodeWatcher(int time,String path) {
			super();
			this.time = time;
			this.path = path;
		}

		@Override
		public void process(WatchedEvent event) {
			System.out.println("=====================start：" + time);
			System.out.println("state:" + event.getState());
			System.out.println("type:" + event.getType());
			System.out.println("path:" + event.getPath());
			System.out.println("=====================end:" + time);
			if (event.getState() == KeeperState.SyncConnected) {
				if (event.getType() == EventType.NodeDataChanged) {
					try {
						String data = new String(getData(path));
						System.out.println("节点值修改了，当前值为：" + data);
					} catch (KeeperException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			if(event.getType() == EventType.NodeDeleted){
				CommCache.removeNode(path);
				System.out.println(path+"下线");
			}
			try {
				zookeeper.exists(path, new NodeWatcher(time + 1,path));
			} catch (KeeperException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
