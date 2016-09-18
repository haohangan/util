package com.eva.math.zk.operate.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.eva.math.zk.conn.ZKConn;
import com.eva.math.zk.operate.ZKManager;

public class ZKManagerImpl implements ZKManager {

	// private ZKConn conn;
	private ZooKeeper zk;

	public ZKManagerImpl(ZKConn conn) {
		super();
		// this.conn = conn;
		zk = conn.getZK();
	}

	@Override
	public void create(String path, byte[] data, boolean persisted) throws KeeperException, InterruptedException {
		if (persisted) {
			zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} else {
			zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		}
	}

	@Override
	public void delete(String path) throws KeeperException, InterruptedException {
		int version = zk.exists(path, true).getVersion();
		zk.delete(path, version);
	}

	@Override
	public void update(String path, byte[] data) throws KeeperException, InterruptedException {
		int version = zk.exists(path, true).getVersion();
		zk.setData(path, data, version);
	}

	@Override
	public Stat getZNodeStats(String path) throws KeeperException, InterruptedException {
		return zk.exists(path, true);
	}

	@Override
	public String getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException {
		Stat stat = getZNodeStats(path);
		String rtnData = null;
		if (stat != null) {
			byte[] bytes = null;
			if (watchFlag) {
				ZKWatcher watcher = new ZKWatcher();
				bytes = zk.getData(path, watcher, stat);
				watcher.await();
			} else {
				bytes = zk.getData(path, false, stat);
			}
			rtnData = new String(bytes, StandardCharsets.UTF_8);
		}
		return rtnData;
	}

	@Override
	public List<String> getZNodeChildren(String path) throws KeeperException, InterruptedException {
		Stat stat = getZNodeStats(path);
		List<String> list = null;
		if (stat != null) {
			list = zk.getChildren(path, false);
		}
		return list;
	}

}
