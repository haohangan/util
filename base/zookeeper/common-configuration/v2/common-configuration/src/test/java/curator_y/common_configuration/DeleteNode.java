package curator_y.common_configuration;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Test;

public class DeleteNode {
    @Test
    public void test() throws Exception{
    	RetryPolicy retryPolicy = new ExponentialBackoffRetry(3*1000,3);
    	CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
    	client.start();
    	client.delete().deletingChildrenIfNeeded().forPath("/discovery");
    	CloseableUtils.closeQuietly(client);
    }
}
