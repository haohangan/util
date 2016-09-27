package com.eva.rpc.curatorExample.getstart;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Demo1 {
	public static void main(String[] args) {
		String connectString = "localhost:2182";
		// int sessionTimeoutMs = 3000;
		// int connectionTimeoutMs = 30*60*1000;
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
		client.start();
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		client.close();
	}
}
