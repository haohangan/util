package com.eva.math.zk;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.eva.math.zk.conn.ZKConn;
import com.eva.math.zk.operate.ZKManager;
import com.eva.math.zk.operate.impl.ZKManagerImpl;

public class ConnTest {

	ZKConn conn;

	@Before
	public void init() throws IOException, Exception {
		conn = new ZKConn();
		Logger.getGlobal().setLevel(Level.INFO);
	}

	@Test
	public void test() throws Exception {
		System.out.println(conn.getZK());
		Thread.sleep(1000 * 10);
		conn.close();
	}

	@Test
	public void testCreate() throws Exception {
		ZKManager manager = new ZKManagerImpl(conn);
		manager.create("/gh", "{a:k47}".getBytes(), false);
		System.out.println(manager.getZNodeData("/gh", false));
		Thread.sleep(1000 * 20);
		conn.close();
	}

	@Test
	public void testChildren() throws Exception {
		ZKManager manager = new ZKManagerImpl(conn);
		String p = "/gh";
		try{
			manager.create(p, "{a:k47}".getBytes(), true);
		}catch(Exception e){
			
		}
		manager.create(p + "/hhh", "{a:k48}".getBytes(), false);
		System.out.println(manager.getZNodeChildren(p).size());
		Thread.sleep(1000 * 10);
		manager.delete(p + "/hhh");
		manager.delete(p);
		conn.close();
	}
}
