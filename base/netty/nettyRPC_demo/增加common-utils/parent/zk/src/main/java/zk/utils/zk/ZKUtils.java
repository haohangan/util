package zk.utils.zk;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import zk.utils.vo.NodeInfo;
import zk.utils.vo.PathNode;

@Deprecated
public enum ZKUtils {
	INSTANCE;

	static final String HOST = "127.0.0.1";
	static final int PORT = 2181;
	static final int sessionTimeout = 3000;
	static final Logger LOG = Logger.getGlobal();

	private ZooKeeper zk;
//	private CountDownLatch connSemaphore = new CountDownLatch(1);

	public void init(String host, int port, PathNode pn) throws IOException {
		String connURL = null;
		if (StringUtils.isNoneBlank(host) && port > 0) {
			connURL = host + ":" + port;
		} else {
			connURL = HOST + ":" + PORT;
		}
		zk = new ZooKeeper(connURL, sessionTimeout, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				LOG.info("收到事件通知:" + event.getState() + "\n");
				if (KeeperState.SyncConnected == event.getState()) {
//					connSemaphore.countDown();
				}
			}
		});

		try {
			for (NodeInfo ni : pn.getNodelist()) {
				zk.create(pn.getPath()+"/"+ni.getName(), ni.toJson(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			}
		} catch (KeeperException e) {
			LOG.info("创建结果失败:");
			e.printStackTrace();
		} catch (InterruptedException e) {
			LOG.info("创建结果失败:");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 关闭zk
	 */
	public void close(){
		if(zk!=null){
			try {
				zk.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
