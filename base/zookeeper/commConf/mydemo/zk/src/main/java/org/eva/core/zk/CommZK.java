package org.eva.core.zk;

import java.io.IOException;

/**
 * singleton
 * 
 * @author 97617
 *
 */
public enum CommZK {
	ZKMANAGER;

	ZKManager manager;

	public void conn(String connectString) {
		try {
			manager = new ZKManager(ZKConn.getConn(connectString));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清理连接
	 */
	public void clear() {
		manager.close();
		manager = null;
	}

	public ZKManager getManager() {
		return manager;
	}
}
