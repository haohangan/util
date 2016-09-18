package com.eva.math.zk.operate;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

public interface ZKManager {
	void create(String path, byte[] data,boolean persisted) throws KeeperException, InterruptedException;

	void delete(String path) throws KeeperException, InterruptedException;

	void update(String path, byte[] data) throws KeeperException, InterruptedException;

	Stat getZNodeStats(String path) throws KeeperException, InterruptedException;

	String getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException;

	List<String> getZNodeChildren(String path) throws KeeperException, InterruptedException;
}
