package wrench.zk.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.curator.framework.CuratorFramework;

public class ClientPool extends GenericObjectPool<CuratorFramework> {

	/**
	 * Constructor.
	 * 
	 * It uses the default configuration for pool provided by
	 * apache-commons-pool2.
	 * 
	 * @param factory
	 */
	public ClientPool(PooledObjectFactory<CuratorFramework> factory) {
		super(factory);
	}

	/**
	 * 
	 * 
	 * @param factory
	 * @param config
	 */
	public ClientPool(PooledObjectFactory<CuratorFramework> factory, GenericObjectPoolConfig config) {
		super(factory, config);
	}

}
