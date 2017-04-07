package wrench.zk.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.curator.framework.CuratorFramework;

public class PoolTest {
	public static void main(String[] args) throws Exception {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxIdle(1);
		config.setMaxTotal(10);

		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		ClientPool pool = new ClientPool(new ClientPoolFactory("localhost:2181"), config);
		
		for(int i = 0;i<100;i++){
			CuratorFramework borrowedObj = pool.borrowObject();
			byte[] bytes= borrowedObj.getData().forPath("/demo/app1");
			System.out.println((i+1)+":"+new String(bytes));
			pool.returnObject(borrowedObj);
		}
		WaitUtil.waitInput();
	}
}
