package zk.test;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zk.utils.zk.ZKManagerImpl;

public class ZKClientTest {
	ZKManagerImpl zkmanager;
	String path = "/QN-GBZnode";
	byte[] data = "www.java.globinch.com ZK Client Data".getBytes();

	@Before
	public void init() {
		zkmanager = new ZKManagerImpl();
	}

	@After
	public void tearDown() throws Exception {
		zkmanager.closeConnection();
	}

	@Test
	public void testCreate() throws KeeperException, InterruptedException {
		// data in byte array

		zkmanager.create(path, data);
		Stat stat = zkmanager.getZNodeStats(path);
		System.out.println(stat);
	}
	
	@Test
	public void testDelete() throws KeeperException, InterruptedException {
		// data in byte array
		zkmanager.delete(path);
	}
	
	@Test
	public void testGetZNodeData() throws KeeperException, InterruptedException {
		System.out.println(zkmanager.getZNodeData(path,false));
	}
	
	@Test
	public void testUpdate() throws KeeperException, InterruptedException {
		String data = "666666666666666666666";
		byte[] dataBytes = data.getBytes();
		zkmanager.update(path, dataBytes);
	}
}
