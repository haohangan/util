package curator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

public class LeaderElectionServer {
	static String CONN = "localhost:2180";
	static String ELE_PATH = "/election";

	/**
	 * 主程序
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		CuratorFramework client = create();
		String path = ELE_PATH + "/guide_";
		String mypath = client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, new byte[0]);
		System.out.println("current node is:" + mypath);
		showLeader(client, mypath);
		client.getChildren().usingWatcher(watcher(client, mypath)).forPath(ELE_PATH);
		System.out.println("Press enter/return to quit\n");
		new BufferedReader(new InputStreamReader(System.in)).readLine();// 等待退出
		CloseableUtils.closeQuietly(client);

	}

	/**
	 * 查看leader
	 * 
	 * @param client
	 * @param mypath
	 * @throws Exception
	 */
	private static void showLeader(CuratorFramework client, String mypath) throws Exception {
		List<String> children = client.getChildren().forPath(ELE_PATH);
		if (children != null && children.size() > 0) {
			String min = children.get(0);
			for (int i = 1; i < children.size(); i++) {
				if (min.compareTo(children.get(i)) > 0) {
					min = children.get(i);
				}
			}
			System.out.println(mypath + " says:leader is " + min);
		}
	}

	/**
	 * 监听子节点变化
	 * 
	 * @param client
	 * @param mypath
	 * @return
	 */
	static CuratorWatcher watcher(final CuratorFramework client, final String mypath) {
		return new CuratorWatcher() {
			@Override
			public void process(WatchedEvent event) throws Exception {
				if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
					showLeader(client, mypath);
					client.getChildren().usingWatcher(watcher(client, mypath)).forPath(ELE_PATH);
				}
			}
		};
	}

	/**
	 * 创建连接
	 * 
	 * @return
	 * @throws Exception
	 */
	static CuratorFramework create() throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient(CONN, retryPolicy);
		client.start();
		Stat stat = client.checkExists().forPath(ELE_PATH);
		if (stat == null) {
			client.create().withMode(CreateMode.PERSISTENT).forPath(ELE_PATH,
					"leader election!".getBytes(StandardCharsets.UTF_8));
		}
		return client;
	}
}
