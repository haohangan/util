package jersey.simple_server.discovery;

import java.io.Closeable;
import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

public class ServerService implements Closeable {
	private final ServiceDiscovery<InstanceDetails> serviceDiscovery;
	private final ServiceInstance<InstanceDetails> serviceInstance;

	public ServerService(CuratorFramework client, InstanceDetails detail, UriSpec uriSpec) throws Exception {
		serviceInstance = ServiceInstance.<InstanceDetails> builder().name(ServerConfig.APP_NAME).payload(detail)
				.address(ServerConfig.address).port(ServerConfig.port).uriSpec(uriSpec).build();

		JsonInstanceSerializer<InstanceDetails> serializer = new JsonInstanceSerializer<>(InstanceDetails.class);

		serviceDiscovery = ServiceDiscoveryBuilder.builder(InstanceDetails.class).client(client)
				.basePath(ServerConfig.PATH).serializer(serializer).thisInstance(serviceInstance).build();
	}

	public ServiceDiscovery<InstanceDetails> getServiceDiscovery() {
		return serviceDiscovery;
	}

	public ServiceInstance<InstanceDetails> getServiceInstance() {
		return serviceInstance;
	}

	@Override
	public void close() throws IOException {
		CloseableUtils.closeQuietly(serviceDiscovery);
	}

	public void start() throws Exception {
		serviceDiscovery.start();
	}
}
