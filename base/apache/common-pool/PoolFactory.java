package wrench.zk.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

public class ClientPoolFactory extends BasePooledObjectFactory<CuratorFramework> {
	private String connectString;
	
	

	public ClientPoolFactory(String connectString) {
		super();
		this.connectString = connectString;
	}

	@Override
	public CuratorFramework create() throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
		client.start();
		return client;
	}

	@Override
	public PooledObject<CuratorFramework> wrap(CuratorFramework obj) {
		return new DefaultPooledObject<CuratorFramework>(obj);
	}

	@Override
	public void destroyObject(PooledObject<CuratorFramework> p) throws Exception {
		CloseableUtils.closeQuietly(p.getObject());
		super.destroyObject(p);
	}
}
