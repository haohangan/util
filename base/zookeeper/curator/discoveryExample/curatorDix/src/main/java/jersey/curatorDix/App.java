package jersey.curatorDix;

import java.util.Scanner;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

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

		InstanceDetails detail = new InstanceDetails("this is a demo,under stand");
		UriSpec uriSpec = new UriSpec("{scheme}://{address}:{port}/simple1/demo");// http://localhost:90/simple1/demo

		final ServiceInstance<InstanceDetails> demoInstance = ServiceInstance.<InstanceDetails> builder()
				.name("simple1").payload(detail).address("localhost").port(90).uriSpec(uriSpec).build();

		JsonInstanceSerializer<InstanceDetails> serializer = new JsonInstanceSerializer<>(InstanceDetails.class);

		final ServiceDiscovery<InstanceDetails> serviceDiscovery = ServiceDiscoveryBuilder
				.builder(InstanceDetails.class).client(client).basePath("/test").serializer(serializer)
				.thisInstance(demoInstance).build();
		serviceDiscovery.start();

		Scanner scanner = new Scanner(System.in);
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(System.in));
		boolean done = false;
		while (!done) {
			System.out.print("> ");
			String line = scanner.nextLine();
			if (line == null) {
				break;
			}
			String command = line.trim();
			if (command.equalsIgnoreCase("quit")) {
				done = true;
			} else if (command.equals("list")) {
				for (String name : serviceDiscovery.queryForNames()) {
					System.out.println("service name:" + name);
					for (ServiceInstance<InstanceDetails> instance : serviceDiscovery.queryForInstances(name)) {
						System.out.println(
								"\t" + instance.getPayload().getDescription() + ": " + instance.buildUriSpec());
					}
				}
			} else if (command.equals("update")) {
				InstanceDetails newDetail = new InstanceDetails("new details");
				ServiceInstance<InstanceDetails> changedInstance = ServiceInstance.<InstanceDetails> builder()
						.id(demoInstance.getId()).address(demoInstance.getAddress()).payload(newDetail)
						.name(demoInstance.getName()).port(demoInstance.getPort()).uriSpec(uriSpec).build();
				serviceDiscovery.updateService(changedInstance);
				System.out.println("更新了服务");
			} else {
				System.out.println("重新输入");
			}
		}

		scanner.close();
		CloseableUtils.closeQuietly(serviceDiscovery);
		CloseableUtils.closeQuietly(client);
		System.out.println("服务终止...");
	}
}
