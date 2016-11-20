package curator_y.common_configuration.curator;

import java.nio.charset.StandardCharsets;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CuratorStart implements DisposableBean, InitializingBean {
	@Value("${server.appname}")
	private String APP_NAME;

	@Value("${zookeeper.connectString}")
	private String connectString;

	@Value("${zookeeper.sessionTimeout}")
	private int sessionTimeout = 5;

	@Value("${zookeeper.connectionTimeout}")
	private int connectionTimeout = 30;

	CuratorFramework client;
	PathChildrenCache cache;

	@Override
	public void destroy() throws Exception {
		System.out.println("zookeeper关闭连接");
		CloseableUtils.closeQuietly(client);
		CloseableUtils.closeQuietly(cache);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String path = "/" + APP_NAME;
		System.out.println("初始化zookeeper连接...");
		System.out.println("connectString:" + connectString);
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.newClient(connectString, sessionTimeout * 1000, connectionTimeout * 1000,
				retryPolicy);
		client.start();
		ZookeeperManager.createOrUpdate(client, path, "register center".getBytes(StandardCharsets.UTF_8));

		cache = new PathChildrenCache(client, path, true);
		cache.start();
		ZookeeperManager.addListener(cache);
		System.out.println("初始化zookeeper连接结束...");
	}
	
	public void list(){
		ZookeeperManager.list(cache);
	}
}
