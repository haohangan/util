package curator_demo.curator_client;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceCache;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.ServiceCacheListener;

import jersey.curatorDix.discovery.InstanceDetails;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		final CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181",
				new ExponentialBackoffRetry(1000, 3));
		client.start();

		final ServiceDiscovery<InstanceDetails> serviceDiscovery = ServiceDiscoveryBuilder
				.builder(InstanceDetails.class).client(client).basePath("/test").build();
		serviceDiscovery.start();

		Collection<ServiceInstance<InstanceDetails>> colls = serviceDiscovery.queryForInstances("simple1");
		System.out.println("size:" + colls.size());
		for (ServiceInstance<InstanceDetails> instance : colls) {
			System.out.println(instance.getPayload().getDescription() + ":" + instance.buildUriSpec());
		}
		final CountDownLatch latch = new CountDownLatch(3);
		final ServiceCache<InstanceDetails> cache = serviceDiscovery.serviceCacheBuilder().name("simple1").build();
		ServiceCacheListener listener = new ServiceCacheListener() {

			public void stateChanged(CuratorFramework client, ConnectionState newState) {
				System.out.println(client + "\t" + newState);
			}

			public void cacheChanged() {
				System.out.println("数据变了");
				latch.countDown();
				for (ServiceInstance<InstanceDetails> instance : cache.getInstances()) {
					System.out.println(instance.getPayload().getDescription() + ":\t" + instance.buildUriSpec());
				}
			}
		};
		cache.addListener(listener);
		cache.start();
		latch.await();
		CloseableUtils.closeQuietly(serviceDiscovery);
		CloseableUtils.closeQuietly(client);
		CloseableUtils.closeQuietly(cache);
		System.out.println("client jieshu");
	}
}
