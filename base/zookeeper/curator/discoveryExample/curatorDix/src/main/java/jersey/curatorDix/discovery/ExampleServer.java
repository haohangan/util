package jersey.curatorDix.discovery;

import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;

public class ExampleServer {
	private final ServiceDiscovery<InstanceDetails> serviceDiscovery;

	private final ServiceInstance<InstanceDetails> serviceInstance;

	private ExampleServer(ServiceDiscovery<InstanceDetails> serviceDiscovery,
			ServiceInstance<InstanceDetails> serviceInstance) {
		super();
		this.serviceDiscovery = serviceDiscovery;
		this.serviceInstance = serviceInstance;
	}
	
	
	

	public ServiceDiscovery<InstanceDetails> getServiceDiscovery() {
		return serviceDiscovery;
	}

	public ServiceInstance<InstanceDetails> getServiceInstance() {
		return serviceInstance;
	}
	
	

}
