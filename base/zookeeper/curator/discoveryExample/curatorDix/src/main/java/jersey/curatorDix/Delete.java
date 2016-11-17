package jersey.curatorDix;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

public class Delete {
	public static void main(String[] args) {
		final CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181",
				new ExponentialBackoffRetry(1000, 3));
		client.start();

		try {
			client.delete().deletingChildrenIfNeeded().forPath("/demo");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
			System.out.println("结束连接");
		}
	}
}
