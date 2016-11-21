package jersey.simple_server.discovery;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.curator.RetryPolicy;
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

import com.google.common.collect.Maps;

public enum ServiceManager implements Closeable {
	SERVICE;

//	private static Logger logger = Logger.getLogger(ServiceManager.class.getName());

	ServiceDiscovery<InstanceDetails> serviceDiscovery = null;
	CuratorFramework client;
	ServiceCache<InstanceDetails> cache;
	Map<String, String> urls = Maps.newConcurrentMap();;

	public void start() throws Exception {
		System.out.println("ServiceManager start");
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(3 * 1000, 3);
		client = CuratorFrameworkFactory.newClient(ServerConfig.connectString, retryPolicy);
		client.start();

		final ServiceDiscovery<InstanceDetails> serviceDiscovery = ServiceDiscoveryBuilder
				.builder(InstanceDetails.class).client(client).basePath(ServerConfig.PATH).build();
		serviceDiscovery.start();
		
		cache = serviceDiscovery.serviceCacheBuilder().name(ServerConfig.APP_NAME).build();
		cache.addListener(new ServiceCacheListener() {

			@Override
			public void stateChanged(CuratorFramework client, ConnectionState newState) {

			}

			@Override
			public void cacheChanged() {
				System.out.println("数据变了");
				for (ServiceInstance<InstanceDetails> instance : cache.getInstances()) {
					System.out.println(instance.getPayload().getDescription() + ":\t" + instance.buildUriSpec());
				}
				cache();
			}
		});
		cache.start();
		cache();
	}
	
	public void cache(){
		urls.clear();
		List<ServiceInstance<InstanceDetails>> list = cache.getInstances();
		for(ServiceInstance<InstanceDetails> instance : list){
			String url= instance.buildUriSpec();
			String name = instance.getPayload().getName();
			urls.put(name, url);
		}
	}
	
	public String get(String name){
		return urls.get(name);
	}

	@Override
	public void close() throws IOException {
		CloseableUtils.closeQuietly(client);
		CloseableUtils.closeQuietly(serviceDiscovery);
		CloseableUtils.closeQuietly(cache);
	}

}
