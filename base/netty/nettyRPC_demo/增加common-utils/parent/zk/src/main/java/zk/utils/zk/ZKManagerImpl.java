package zk.utils.zk;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import log.CommonLogger;

public class ZKManagerImpl implements ZKManager {

	private static ZooKeeper zkeeper;
	private static ZKConnection zkConnection;

	public ZKManagerImpl() {
		initialize();
	}

	public void initialize() {
		zkConnection = new ZKConnection();
		try {
			zkeeper = zkConnection.connect("localhost");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close the zookeeper connection
	 */
	public void closeConnection() {
		try {
			zkConnection.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void create(String path, byte[] data) throws KeeperException, InterruptedException {
		CommonLogger.info("create node:" + path);
		zkeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}

	@Override
	public void delete(String path) throws KeeperException, InterruptedException {
		int version = zkeeper.exists(path, true).getVersion();
		CommonLogger.info("delete node:" + path + ",version:" + version);
		zkeeper.delete(path, version);
	}

	@Override
	public void update(String path, byte[] data) throws KeeperException, InterruptedException {
		int version = zkeeper.exists(path, true).getVersion();
		CommonLogger.info("update node:" + path + ",version:" + version);
		zkeeper.setData(path, data, version);

	}

	@Override
	public Stat getZNodeStats(String path) throws KeeperException, InterruptedException {
		Stat stat = zkeeper.exists(path, true);
		if (stat != null) {
			CommonLogger.info("Node exists and the node version is " + stat.getVersion());
		} else {
			CommonLogger.info("Node does not exists");
		}
		return stat;
	}

	@Override
	public String getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException {
		Stat stat = getZNodeStats(path);
		byte[] b = null;
		try {
			if (stat != null) {
				if (watchFlag) {
					ZKWatcher watch = new ZKWatcher();
					b = zkeeper.getData(path, watch, null);
					watch.await();
				} else {
					b = zkeeper.getData(path, null, null);
				}

				String data = new String(b, "UTF-8");
				CommonLogger.info(data);

				return data;
			} else {
				CommonLogger.info("Node does not exists");
			}
		} catch (Exception e) {
			CommonLogger.info(e.getMessage());
		}
		return null;
	}

	@Override
	public List<String> getZNodeChildren(String path) throws KeeperException, InterruptedException {
		Stat stat = getZNodeStats(path);
		List<String> children = null;

		if (stat != null) {
			children = zkeeper.getChildren(path, false);
			for (int i = 0; i < children.size(); i++)
				CommonLogger.info(children.get(i));

		} else {
			CommonLogger.info("Node does not exists");
		}
		return children;
	}

}
