package jersey.simple_server;

import java.io.IOException;
import java.util.List;

import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;

import jersey.simple_server.discovery.InstanceDetails;
import jersey.simple_server.discovery.ServerConfig;
import jersey.simple_server.discovery.ServiceManager;

public class ServiceStart {
	public static void initService() throws Exception {
		System.out.println("开始注册服务");
		InstanceDetails detail1 = new InstanceDetails("hello", "http", "hello demo");
		InstanceDetails detail2 = new InstanceDetails("hello/json", "http", "hello json");

		ServiceManager.SERVICE.start();
		
		ServiceManager.SERVICE.add(detail1,
				new UriSpec("{scheme}://{address}:{port}/" + ServerConfig.APP_NAME + "/" + detail1.getName()));
		ServiceManager.SERVICE.add(detail2,
				new UriSpec("{scheme}://{address}:{port}/" + ServerConfig.APP_NAME + "/" + detail2.getName()));
	}

	public static void close() throws IOException {
		ServiceManager.SERVICE.close();
	}

	public static List<ServiceInstance<InstanceDetails>> list() throws Exception {
		return ServiceManager.SERVICE.listInstances();
	}
}
