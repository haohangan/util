package curator_y.common_configuration.curator;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class ZookeeperManager {
	private static Logger logger = Logger.getLogger(ZookeeperManager.class.getName());

	/**
	 * 创建或更新节点
	 * 
	 * @param client
	 * @param path
	 * @param data
	 */
	public static void createOrUpdate(CuratorFramework client, String path, byte[] data) {
		try {
			Stat stat = client.checkExists().forPath(path);
			if (stat == null) {
				client.create().withMode(CreateMode.PERSISTENT).forPath(path, data);
			} else {
				client.setData().forPath(path, data);
			}
		} catch (Exception e) {
			logger.log(Level.ALL, "创建节点出错", e);
		}
	}

	/**
	 * 监听cache变化
	 * 
	 * @param cache
	 */
	public static void addListener(PathChildrenCache cache) {
		PathChildrenCacheListener listener = new PathChildrenCacheListener() {

			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				switch (event.getType()) {
				case CHILD_ADDED: {
					System.out.println("CHILD_ADDED:" + ZKPaths.getNodeFromPath(event.getData().getPath()));
					break;
				}
				case CHILD_UPDATED: {
					System.out.println("CHILD_UPDATED:" + ZKPaths.getNodeFromPath(event.getData().getPath()));
					break;
				}
				case CHILD_REMOVED: {
					System.out.println("CHILD_REMOVED:" + ZKPaths.getNodeFromPath(event.getData().getPath()));
					break;
				}
				default:
					System.out.println("other condition:" + event.getType().toString());
				}
			}
		};
		cache.getListenable().addListener(listener);
	}

	/**
	 * 查看所有child节点
	 * 
	 * @param cache
	 */
	public static void list(PathChildrenCache cache) {
		if (cache.getCurrentData().size() == 0) {
			System.out.println("* empty *");
		} else {
			for (ChildData data : cache.getCurrentData()) {
				System.out.println(data.getPath() + " = " + new String(data.getData()));
			}
		}
	}
}
