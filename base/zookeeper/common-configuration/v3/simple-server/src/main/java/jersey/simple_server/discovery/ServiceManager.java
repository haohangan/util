package jersey.simple_server.discovery;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import com.google.common.collect.Maps;

public enum ServiceManager implements Closeable {
	SERVICE;

	private static Logger logger = Logger.getLogger(ServiceManager.class.getName());

	ConcurrentHashMap<String, ServerService> map;
	ServiceDiscovery<InstanceDetails> serviceDiscovery = null;
	CuratorFramework client;
	Map<String, ServiceProvider<InstanceDetails>>   providers;

	public void start() throws Exception {
		System.out.println("ServiceManager start");
		map = new ConcurrentHashMap<>();
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(3 * 1000, 3);
		client = CuratorFrameworkFactory.newClient(ServerConfig.connectString,5*1000,3*1000, retryPolicy);
		client.start();

		JsonInstanceSerializer<InstanceDetails> serializer = new JsonInstanceSerializer<InstanceDetails>(
				InstanceDetails.class);
		serviceDiscovery = ServiceDiscoveryBuilder.builder(InstanceDetails.class).client(client)
				.basePath(ServerConfig.PATH).serializer(serializer).build();
		serviceDiscovery.start();
		
		providers = Maps.newHashMap();
		
	}

	public void add(InstanceDetails detail, UriSpec uriSpec) {
		try {
			ServerService service = new ServerService(client, detail, uriSpec);
			service.start();
			map.put(detail.getName(), service);
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, "添加服务出错", e);
			e.printStackTrace();
		}
	}

	public List<ServiceInstance<InstanceDetails>> listInstances() throws Exception {
//		ServiceDiscovery<InstanceDetails> serviceDiscovery = service.getServiceDiscovery();
		Collection<String> serviceNames = serviceDiscovery.queryForNames();
		System.out.println(serviceNames.size() + " type(s)");
		List<ServiceInstance<InstanceDetails>> list = new ArrayList<>();
		for (String serviceName : serviceNames) {
			Collection<ServiceInstance<InstanceDetails>> instances = serviceDiscovery.queryForInstances(serviceName);
			System.out.println(serviceName);
			for (ServiceInstance<InstanceDetails> instance : instances) {
				outputInstance(instance);
				list.add(instance);
			}
		}
		return list;
	}

	private void outputInstance(ServiceInstance<InstanceDetails> instance) {
		System.out.println(
				instance.getName() + "\t" + instance.getPayload().getDescription() + ": " + instance.buildUriSpec());
	}

	@Override
	public void close() throws IOException {
//		for (Entry<String, ServerService> entry : map.entrySet()) {
//			entry.getValue().close();
//		}
		CloseableUtils.closeQuietly(client);
		CloseableUtils.closeQuietly(serviceDiscovery);
	}

}
